package learnlanguages.hk.com.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.LearnModel;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.interfacies.RecyclerViewOnClickListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.new_version.controllers.DataController_;
import learnlanguages.hk.com.utils.AnimUtils;
import learnlanguages.hk.com.utils.LogUtils;
import learnlanguages.hk.com.utils.SoundHelper;

/**
 * Created by Hovhannisyan.Karo on 03.12.2017.
 */

public class LearnPagerAdapter extends PagerAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<LearnModel> animalsData;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public LearnPagerAdapter(RecyclerViewOnClickListener recyclerViewOnClickListener) {
        mContext = ViewController.getViewController().getContex();
        mLayoutInflater = LayoutInflater.from(mContext);
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    public void setData(int categoryId) {
        resetData();
        switch (categoryId) {
            case Constants.CATEGORY.AQUATIC:
                animalsData = DataController_.getInstance().getLearnModelSea();
                LogUtils.d("aquatic_hk", animalsData.toString());
                break;
            case Constants.CATEGORY.BIRDS:
                animalsData = DataController_.getInstance().getLearnModelBirds();
                break;
            case Constants.CATEGORY.DOMESTIC:
                animalsData = DataController_.getInstance().getLearnModelDomestic();
                break;
            case Constants.CATEGORY.WHILD:
                animalsData = DataController_.getInstance().getLearnModelWild();
                break;
            case Constants.CATEGORY.COLOR:
                animalsData = DataController_.getInstance().getLearnModelColors();
                break;
        }
        notifyDataSetChanged();
    }


    private void resetData() {
        this.animalsData = null;
    }

    @Override
    public int getCount() {
        return animalsData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    private ImageView ivGoRight;
    private ImageView ivGoLeft;
    private ImageView ivLearnPNG;


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_learn_page, container, false);

        RelativeLayout llLearn = (RelativeLayout) itemView.findViewById(R.id.ll_learn);
        RelativeLayout llEname = (RelativeLayout) itemView.findViewById(R.id.rl_eName);
        RelativeLayout llOname = (RelativeLayout) itemView.findViewById(R.id.rl_pname);
        ivLearnPNG = (ImageView) itemView.findViewById(R.id.iv_learn_image);
        ivGoRight = (ImageView) itemView.findViewById(R.id.iv_go_right);
        ivGoLeft = (ImageView) itemView.findViewById(R.id.iv_go_left);
        TextView tvEName = (TextView) itemView.findViewById(R.id.tv_learn_ename);
        TextView tvArmName = (TextView) itemView.findViewById(R.id.tv_learn_pname);

        ivLearnPNG.setImageResource(animalsData.get(position).getImage(mContext));
        tvEName.setText(animalsData.get(position).getEnName());
        tvArmName.setText(animalsData.get(position).getArmName(mContext));

        ivGoRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClickListener.itemClicked(view, position);
            }
        });

        ivGoLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewOnClickListener.itemClicked(view, position);
            }
        });

        ivLearnPNG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLearnPNG.requestLayout();
//                ivLearnPNG.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_vibration));
                recyclerViewOnClickListener.itemClicked(view, position);
            }
        });


        llEname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
                    @Override
                    public void onEnd() {
                        recyclerViewOnClickListener.itemClicked(view, position);
                    }
                });
            }
        });

        llOname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
                    @Override
                    public void onEnd() {
                        recyclerViewOnClickListener.itemClicked(view, position);
                    }
                });
            }
        });

        container.addView(llLearn);
        return itemView;
    }

    public void playCurrentAnimalVoic(final int position) {
        SoundHelper.getInstance().playTrack(animalsData.get(position).getEnVoice(mContext), onPlayCompliteListener);
//        ivLearnPNG.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_vibration));
    }

    private OnPlayCompliteListener onPlayCompliteListener = new OnPlayCompliteListener() {
        @Override
        public void onComplite() {

        }
    };


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


}