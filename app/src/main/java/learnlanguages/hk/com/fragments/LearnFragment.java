package learnlanguages.hk.com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import learnlanguages.hk.com.activities.LearnActivity;
import learnlanguages.hk.com.adapters.LearnPagerAdapter;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.LearnModel;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.interfacies.RecyclerViewOnClickListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.new_version.controllers.DataController_;
import learnlanguages.hk.com.utils.AnimUtils;
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
        if (getActivity() instanceof LearnActivity) {
            ((LearnActivity) getActivity()).setCategoryViewVisibility(false);
        }
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
        DataController_.getInstance().setCategory(categoryId);
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
            AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
                @Override
                public void onEnd() {
                    if (pager.getCurrentItem() < adapter.getCount() - 1) {
                        pager.setCurrentItem(pager.getCurrentItem() + 1);
                    }
                }
            });

        } else if (viewId == R.id.iv_go_left) {
            AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
                @Override
                public void onEnd() {
                    if (pager.getCurrentItem() > 0) {
                        pager.setCurrentItem(pager.getCurrentItem() - 1);
                    }
                }
            });
        } else {
            LearnModel currentAnimal = null;
            int category = DataController_.getInstance().getCategory();
            switch (category) {
                case Constants.CATEGORY.BIRDS:
                    currentAnimal = DataController_.getInstance().getLearnModelBirds().get(position);
                    break;
                case Constants.CATEGORY.AQUATIC:
                    currentAnimal = DataController_.getInstance().getLearnModelSea().get(position);
                    break;
                case Constants.CATEGORY.WHILD:
                    currentAnimal = DataController_.getInstance().getLearnModelWild().get(position);
                    break;
                case Constants.CATEGORY.DOMESTIC:
                    currentAnimal = DataController_.getInstance().getLearnModelDomestic().get(position);
                    break;
                case Constants.CATEGORY.COLOR:
                    currentAnimal = DataController_.getInstance().getLearnModelColors().get(position);
                    break;
                default:
                    break;
            }

            switch (view.getId()) {
                case R.id.iv_learn_image:
                    final LearnModel finalCurrentAnimal = currentAnimal;
                    AnimUtils.gupiAnimalAnimate(view, new OnAnimEndAction() {
                        @Override
                        public void onEnd() {
                            if (finalCurrentAnimal.getAnimalVoice(getActivity()) != 0)
                                SoundHelper.getInstance().playTrack(finalCurrentAnimal.getAnimalVoice(getActivity()), new PlayCompleteListenerEmpty());

                        }
                    });
                    break;
                case R.id.rl_eName:
                    SoundHelper.getInstance().playTrack(currentAnimal.getEnVoice(getActivity()), new PlayCompleteListenerEmpty());
                    break;
                case R.id.rl_pname:
                    SoundHelper.getInstance().playTrack(currentAnimal.getArmVoice(getActivity()), new PlayCompleteListenerEmpty());
                    break;
                default:
                    break;
            }
        }
    }

    private class PlayCompleteListenerEmpty implements OnPlayCompliteListener {

        @Override
        public void onComplite() {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        SoundHelper.getInstance().stopPlayer();
        if (getActivity() instanceof LearnActivity) {
            ((LearnActivity) getActivity()).setCategoryViewVisibility(true);
        }
    }
}
