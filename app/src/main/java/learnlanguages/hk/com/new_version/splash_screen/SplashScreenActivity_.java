package learnlanguages.hk.com.new_version.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import learnlanguages.hk.com.activities.PhoneMainActivity;
import learnlanguages.hk.com.interfacies.OnInitFinishListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.ActivityUtils;

public class SplashScreenActivity_ extends AppCompatActivity implements ISplashView {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        ActivityUtils.setFullScreen(this);
        setContentView(R.layout.activity_splash_screen_);
        ButterKnife.bind(this);
        if (presenter == null)
            presenter = new SplashPresenter(this);

        presenter.initLearnModelData(getContext(), new OnInitFinishListener() {
            @Override
            public void onDataInited() {
                startActivity(new Intent(getContext(), PhoneMainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private SplashScreenActivity_ getContext() {
        return this;
    }

}
