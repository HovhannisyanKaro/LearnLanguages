package learnlanguages.hk.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.GU;

public class AdminActivity extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);

        GU.loadImage(iv, "c_i_bat");
    }
}
