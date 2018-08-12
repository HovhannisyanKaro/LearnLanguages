package learnlanguages.hk.com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.ActivityUtils;
import learnlanguages.hk.com.utils.AnimUtils;
import learnlanguages.hk.com.utils.SoundHelper;

public class PhoneMainActivity extends AppCompatActivity {


    @BindView(R.id.iv_phone_call)
    ImageView ivPhoneCall;
    @BindView(R.id.iv_gupi_game)
    ImageView ivGupiGame;
    @BindView(R.id.iv_gupi_colors)
    ImageView ivGupiColors;
    @BindView(R.id.iv_phone_write)
    ImageView ivPhoneWrite;
    @BindView(R.id.iv_phone_bubble)
    ImageView ivPhoneBubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.setFullScreen(this);
        setContentView(R.layout.activity_phone_main);
        ButterKnife.bind(this);
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
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

    }

    @OnClick(R.id.iv_phone_call)
    public void onViewClicked() {
        AnimUtils.gupiPhoneItemAnimate(ivPhoneCall, new OnAnimEndAction() {
            @Override
            public void onEnd() {

            }
        });
    }

    @OnClick({R.id.iv_phone_call, R.id.iv_gupi_game, R.id.iv_gupi_colors, R.id.iv_phone_write, R.id.iv_phone_bubble})
    public void onViewClicked(final View view) {
        AnimUtils.gupiButtonAnimate(view, new OnAnimEndAction() {
            @Override
            public void onEnd() {
                SoundHelper.getInstance().stopPlayer();
                switch (view.getId()) {
                    case R.id.iv_phone_call:
                        startActivity(new Intent(PhoneMainActivity.this, CallActivity.class));
                        break;
                    case R.id.iv_gupi_game:
                        LearnActivity.isColorMode = false;
                        startActivity(new Intent(PhoneMainActivity.this, LearnActivity.class));
                        break;
                    case R.id.iv_gupi_colors:
                        LearnActivity.isColorMode = true;
                        startActivity(new Intent(PhoneMainActivity.this, LearnActivity.class));
                        break;
                    case R.id.iv_phone_write:
                        startActivity(new Intent(PhoneMainActivity.this, WriteActivity.class));
                        break;
                    case R.id.iv_phone_bubble:
                        startActivity(new Intent(PhoneMainActivity.this, PlayActivity.class));
                        break;
                }
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        SoundHelper.getInstance().pauseTrack();
    }
}
