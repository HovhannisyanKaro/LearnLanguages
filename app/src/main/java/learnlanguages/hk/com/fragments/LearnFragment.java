package learnlanguages.hk.com.fragments;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import learnlanguages.hk.com.adapters.LearnPagerAdapter;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Animal;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.interfacies.RecyclerViewOnClickListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.GeneralMetods;
import learnlanguages.hk.com.utils.SoundHelper;


public class LearnFragment extends Fragment implements RecyclerViewOnClickListener {


    @BindView(R.id.iv_background_learn)
    ImageView ivBackgroundLearn;
    Unbinder unbinder;
    @BindView(R.id.pager)
    ViewPager pager;

    private int categoryId;

    private LearnPagerAdapter adapter;


    public LearnFragment() {

    }

    public static LearnFragment newInstance(int categoryId) {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        args.putInt("category_id", categoryId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewController.getViewController().setLearnFragment(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        initListeners();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryId = getArguments().getInt("category_id");
        DataController.getInstance().setCategory(categoryId);
    }

    private void init() {
        setAdapter();
    }

    private void setAdapter() {
        adapter = new LearnPagerAdapter(this);
        adapter.setData(categoryId);
        pager.setAdapter(adapter);
    }


    private void initListeners() {
        pager.addOnPageChangeListener(onPageChangeListener);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.playCurrentAnimalVoic(0);
                    }
                });
            }
        }, 250);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            adapter.playCurrentAnimalVoic(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void itemClicked(View view, int position) {
        int viewId = view.getId();

        if (viewId == R.id.iv_go_right) {
            if (pager.getCurrentItem() < adapter.getCount()-1){
                pager.setCurrentItem(pager.getCurrentItem()+1);
            }
        } else if(viewId == R.id.iv_go_left) {
            if (pager.getCurrentItem() >0){
                pager.setCurrentItem(pager.getCurrentItem()-1);
            }
        }else{
            Animal currentAnimal = null;
            int category = DataController.getInstance().getCategory();
            switch (category) {
                case Constants.CATEGORY.BIRDS:
                    currentAnimal = DataController.getInstance().getBirdAnimals().get(position);
                    break;
                case Constants.CATEGORY.AQUATIC:
                    currentAnimal = DataController.getInstance().getAquaticAnimals().get(position);
                    break;
                case Constants.CATEGORY.WHILD:
                    currentAnimal = DataController.getInstance().getWhildAnimals().get(position);
                    break;
                case Constants.CATEGORY.DOMESTIC:
                    currentAnimal = DataController.getInstance().getDomesticAnimals().get(position);
                    break;
                default:
                    break;
            }

            switch (view.getId()) {
                case R.id.iv_learn_image:
                    SoundHelper.getInstance().playTrack(currentAnimal.getAnimalVoice(), new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {

                        }
                    });
                    break;
                case R.id.rl_eName:
                    SoundHelper.getInstance().playTrack(currentAnimal.geteVoice(), new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {

                        }
                    });
                    break;
                case R.id.rl_pname:
                    SoundHelper.getInstance().playTrack(currentAnimal.getpVoice(), new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {

                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SoundHelper.getInstance().stopPlayer();
    }
}
