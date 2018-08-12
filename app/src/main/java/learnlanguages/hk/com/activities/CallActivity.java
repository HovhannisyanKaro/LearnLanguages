package learnlanguages.hk.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.interfacies.OnAnimEndAction;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.ActivityUtils;
import learnlanguages.hk.com.utils.AnimUtils;
import learnlanguages.hk.com.utils.ToastUtils;

public class CallActivity extends AppCompatActivity {

    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.setFullScreen(this);
        setContentView(R.layout.activity_call);
        ButterKnife.bind(this);
    }


    public void onNumberClicked(final View view) {
        AnimUtils.gupiAnimalAnimate(view, new OnAnimEndAction() {
            @Override
            public void onEnd() {
                tvPhoneNumber.setText(tvPhoneNumber.getText().toString() + ((TextView) view).getText().toString());
            }
        });
    }
}
