package learnlanguages.hk.com.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Game;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.SoundHelper;

public class PlayActivity extends AppCompatActivity {


    @BindView(R.id.iv_background_play)
    ImageView ivBackgroundPlay;
    Unbinder unbinder;
    @BindView(R.id.game)
    Game game;

    public PlayActivity() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_play);
        unbinder = ButterKnife.bind(this);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        ViewController.getViewController().setPlayActivity(this);
    }

    private void init() {
        setBackground();
    }

    private void setBackground() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        SoundHelper.getInstance().stopPlayer();
    }
}
