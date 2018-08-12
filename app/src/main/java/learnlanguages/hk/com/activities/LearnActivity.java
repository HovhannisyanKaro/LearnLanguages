package learnlanguages.hk.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.fragments.LearnFragment;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.AnimUtils;
import learnlanguages.hk.com.utils.SoundHelper;

import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.AQUATIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.BIRDS;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.COLOR;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.DOMESTIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.WHILD;

public class LearnActivity extends AppCompatActivity {

    @BindView(R.id.tv_domestic)
    TextView tvDomestic;
    @BindView(R.id.tv_sea)
    TextView tvSea;
    @BindView(R.id.tv_whild)
    TextView tvWhild;
    @BindView(R.id.tv_birds)
    TextView tvBirds;
    @BindView(R.id.rl_domestic)
    RelativeLayout rlDomestic;
    @BindView(R.id.rl_sea)
    RelativeLayout rlSea;
    @BindView(R.id.rl_whild)
    RelativeLayout rlWhild;
    @BindView(R.id.rl_birds)
    RelativeLayout rlBirds;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.rl_colors)
    RelativeLayout rlColors;
    @BindView(R.id.ll_main)
    LinearLayout llMain;

    public static boolean isColorMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_learn);
        ButterKnife.bind(this);

        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        if (isColorMode) {
            ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(COLOR));
        }

//        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/scribble_box.ttf");
//        tvDomestic.setTypeface(myTypeface);
//        tvSea.setTypeface(myTypeface);
//        tvWhild.setTypeface(myTypeface);
//        tvBirds.setTypeface(myTypeface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isColorMode)
            SoundHelper.getInstance().resumeTrack();

    }

    @Override
    protected void onPause() {
        super.onPause();
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setLearnActivity(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        SoundHelper.getInstance().pauseTrack();

    }

    @OnClick({R.id.rl_domestic, R.id.rl_sea, R.id.rl_whild, R.id.rl_birds, R.id.rl_colors})
    public void onViewClicked(final View view) {

        AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
            @Override
            public void onEnd() {
                switch (view.getId()) {
                    case R.id.rl_domestic:
                        SoundHelper.getInstance().stopPlayer();
                        ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(DOMESTIC));

                        break;
                    case R.id.rl_sea:
                        SoundHelper.getInstance().stopPlayer();
                        ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(AQUATIC));
                        break;
                    case R.id.rl_whild:
                        SoundHelper.getInstance().stopPlayer();
                        ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(WHILD));
                        break;
                    case R.id.rl_birds:
                        SoundHelper.getInstance().stopPlayer();
                        ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(BIRDS));
                        break;
                    case R.id.rl_colors:
                        SoundHelper.getInstance().stopPlayer();
                        ViewController.getViewController().addFragment(R.id.container, LearnFragment.newInstance(COLOR));
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isColorMode) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void setCategoryViewVisibility(boolean isVisibile) {
        if (isVisibile)
            llMain.setVisibility(View.VISIBLE);
        else
            llMain.setVisibility(View.INVISIBLE);
    }
}
