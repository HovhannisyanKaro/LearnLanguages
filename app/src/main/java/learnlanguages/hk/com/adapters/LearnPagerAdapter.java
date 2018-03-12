package learnlanguages.hk.com.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import learnlanguages.hk.com.activities.BubblesActivity;
import learnlanguages.hk.com.activities.LearnActivity;
import learnlanguages.hk.com.activities.MainActivity;
import learnlanguages.hk.com.activities.WriteActivity;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Animal;
import learnlanguages.hk.com.fragments.LevelSelectionFragment;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.interfacies.RecyclerViewOnClickListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.LogUtils;
import learnlanguages.hk.com.utils.SoundHelper;

/**
 * Created by Hovhannisyan.Karo on 03.12.2017.
 */

public class LearnPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    private ArrayList<Animal> animalsData;
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
                animalsData = DataController.getInstance().getAquaticAnimals();
                LogUtils.d("aquatic_hk", animalsData.toString());
                break;
            case Constants.CATEGORY.BIRDS:
                animalsData = DataController.getInstance().getBirdAnimals();
                break;
            case Constants.CATEGORY.DOMESTIC:
                animalsData = DataController.getInstance().getDomesticAnimals();
                break;
            case Constants.CATEGORY.WHILD:
                animalsData = DataController.getInstance().getWhildAnimals();
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
        return view == ((RelativeLayout) object);
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
        Drawable currImage = animalsData.get(position).getImage();

        ivLearnPNG.setImageDrawable(currImage);
        tvEName.setText(animalsData.get(position).geteName());
        tvArmName.setText(animalsData.get(position).getpName());

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
                ivLearnPNG.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_vibration));
                recyclerViewOnClickListener.itemClicked(view, position);
            }
        });

        llEname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        ((LearnActivity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.animate().scaleY(1f).scaleX(1f).start();
                                recyclerViewOnClickListener.itemClicked(view, position);
                            }
                        });
                    }
                }).setDuration(100).start();
            }
        });

        llOname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        ((LearnActivity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                view.animate().scaleY(1f).scaleX(1f).start();
                                recyclerViewOnClickListener.itemClicked(view, position);
                            }
                        });
                    }
                }).setDuration(100).start();
            }
        });


        container.addView(llLearn);
        return itemView;
    }


    public void playCurrentAnimalVoic(int position) {
        SoundHelper.getInstance().playTrack(animalsData.get(position).geteVoice(), onPlayCompliteListener);

        ivLearnPNG.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_vibration));

        if (position>0){
            ivGoLeft.setVisibility(View.VISIBLE);
        }else{
            ivGoLeft.setVisibility(View.INVISIBLE);
        }

        if (position<getCount()-1){
            ivGoRight.setVisibility(View.VISIBLE);
        }else{
            ivGoRight.setVisibility(View.INVISIBLE);
        }
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


    public int calculateAverageColor(Drawable drawable) {

        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int R = 0;
        int G = 0;
        int B = 0;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i++) {
            int color = pixels[i];

            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            n++;
        }
        return Color.rgb(R / n, G / n, B / n);
    }

}