package learnlanguages.hk.com.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.fragments.LevelSelectionFragment;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.AnimUtils;
import learnlanguages.hk.com.utils.LogUtils;
import learnlanguages.hk.com.utils.PrefsUtils;
import learnlanguages.hk.com.utils.SoundHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rl_buttons_cartoon_type)
    LinearLayout rlButtonsCartoonType;
    @BindView(R.id.tv_learn)
    TextView tvLearn;
    @BindView(R.id.tv_play)
    TextView tvPlay;
    @BindView(R.id.cb_mute_unmute)
    CheckBox cbMuteUnmute;
    @BindView(R.id.rl_write)
    RelativeLayout rlWrite;

    {
        //TODO todolist
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        init();
        initListeneres();
        initValuesDependsCartoonOrNot();
        initTextFonts();
        playDefaultSound();

    }

    private void playDefaultSound() {
        SoundHelper.getInstance().playTrack(R.raw.sound_background, true, new OnPlayCompliteListener() {
            @Override
            public void onComplite() {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        SoundHelper.getInstance().resumeTrack();
        if (PrefsUtils.getBooleanValue(MainActivity.this, Constants.SOUND_VALUE)) {
            SoundHelper.getInstance().stopPlayer();
            cbMuteUnmute.setChecked(true);
        } else {
            playDefaultSound();
            cbMuteUnmute.setChecked(false);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        SoundHelper.getInstance().pauseTrack();
    }

    private void initTextFonts() {
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/scribble_box.ttf");
        tvPlay.setTypeface(myTypeface);
        tvLearn.setTypeface(myTypeface);
    }


    private void init() {
    }

    private void initListeneres() {
        cbMuteUnmute.setOnCheckedChangeListener(muteUnmuteCheckedChangeListener);
    }


    public void onViewClicked(final View view) {

        final int viewId = view.getId();

        AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
            @Override
            public void onEnd() {
                if (viewId == R.id.rl_domestic) {
                    startActivity(new Intent(MainActivity.this, LearnActivity.class));
                } else if (viewId == R.id.rl_write) {
                    startActivity(new Intent(MainActivity.this, WriteActivity.class));
                } else if (viewId == R.id.rl_play) {
                    ViewController.getViewController().addFragment(R.id.fl_main_container, new LevelSelectionFragment());
                } else if (viewId == R.id.rl_bubbles) {
                    startActivity(new Intent(MainActivity.this, BubblesActivity.class));
                } else if (viewId == R.id.rl_puzzle) {
                    startActivity(new Intent(MainActivity.this, PuzzleActivity.class));
                }
            }
        });
    }

    private void initValuesDependsCartoonOrNot() {
        setBackground();
    }

    private void setBackground() {
        rlButtonsCartoonType.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (ViewController.getViewController().getFragmentManager().getBackStackEntryCount() > 0) {
            ViewController.getViewController().getFragmentManager().popBackStack();
        } else {
            moveTaskToBack(true);
            startActivity(new Intent(MainActivity.this, PhoneMainActivity.class));
            this.finish();
//            System.exit(0);
        }
        LogUtils.d("onBackPressed", "onBackPressed");
    }

    private void closeApp() {
        SoundHelper.getInstance().playTrack(R.raw.action_sound_quit, new OnPlayCompliteListener() {
            @Override
            public void onComplite() {
                finish();
                System.exit(0);
            }
        });
    }


    private CheckBox.OnCheckedChangeListener muteUnmuteCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (compoundButton.isPressed()) {
                PrefsUtils.putBooleanValue(MainActivity.this, Constants.SOUND_VALUE, b);
                if (b) {
                    SoundHelper.getInstance().stopPlayer();
                } else {
                    playDefaultSound();
                }
            }
        }
    };



}
