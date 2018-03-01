package learnlanguages.hk.com.activities;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.LogUtils;
import learnlanguages.hk.com.utils.SoundHelper;
import learnlanguages.hk.com.utils.ToastUtils;

public class PuzzleActivity extends AppCompatActivity implements View.OnTouchListener {

    @BindView(R.id.ll_options)
    LinearLayout llOptions;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private float x, y, rootX, rootY;
    private float dX, dY;
    private static float squareSizes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_puzzle);
        ButterKnife.bind(this);
        initScreenSizes();
        ToastUtils.t(this, "True");

    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setContex(this);
    }

    private void initScreenSizes() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        y = displayMetrics.heightPixels / displayMetrics.density;
        x = displayMetrics.widthPixels / displayMetrics.density;

        rootX = convertDpToPixel(x);
        rootY = convertDpToPixel(y);
        xz();
        squareSizes = (int) (x - (x / 10));
        squareSizes = convertDpToPixel(squareSizes);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) squareSizes, (int) squareSizes);

        llOptions.setLayoutParams(params);
    }

    private void xz(){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(R.drawable.shape_board);
        imageView.setImageResource(R.drawable.c_i_dove);

        float squareSizes = (int) (x - (x / 10));
        squareSizes = convertDpToPixel(squareSizes) / 2;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) squareSizes, (int) squareSizes);
        imageView.setLayoutParams(params);
        imageView.setRotation(10);
        imageView.animate().x(rootX/2 - squareSizes/2).y(rootY - squareSizes).setDuration(5000).start();
        rlRoot.addView(imageView);
        imageView.setOnTouchListener(this);

        ImageView imageView2 = new ImageView(this);
        imageView2.setBackgroundResource(R.drawable.shape_board);
        imageView2.setImageResource(R.drawable.c_i_dove);

        imageView2.setLayoutParams(params);
        imageView2.setRotation(-10);
        imageView2.animate().x(x / 2).y(y / 2).setDuration(0).start();
        rlRoot.addView(imageView2);
        imageView2.setOnTouchListener(this);
    }

    public float convertDpToPixel(float dp) {
        Resources resources = getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                view.animate()
                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.d("HK_LOG", String.valueOf(squareSizes/2));
                logicOfGame(view.getX(), view.getY());
                break;
            default:
                return false;
        }
        return true;
    }

    private void logicOfGame(float cX, float cY){
        if (cX>0 && cX <squareSizes/2 && cY>0 && cY<squareSizes/2){
            //asdasd
            ToastUtils.t(this, "True");
            SoundHelper.getInstance().playTrack(R.raw.action_sound_skip, new OnPlayCompliteListener() {
                @Override
                public void onComplite() {

                }
            });
        }
    }
}
