package learnlanguages.hk.com.activities;

import android.graphics.Color;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.entities.LearnModel;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.new_version.controllers.DataController_;
import learnlanguages.hk.com.utils.ResUtils;
import learnlanguages.hk.com.utils.SoundHelper;

public class BubblesActivity extends AppCompatActivity implements SensorListener {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private BubblePicker picker;

    private ArrayList<LearnModel> allAnimals;
    private SensorManager sensorMgr;

    long lastUpdate;
    int SHAKE_THRESHOLD = 6000;
    float x, y, z, last_x, last_y, last_z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bubbles);
        ButterKnife.bind(this);
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
        getAllAnimalsAsOneList();
        createBubbles();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void createBubbles() {
        picker = new BubblePicker(this);
        rlRoot.removeAllViews();
        rlRoot.addView(picker);
        picker.setAdapter(adapter);
        picker.setBubbleSize(8);
        ;
        picker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem item) {
                for (int i = 0; i < allAnimals.size(); i++) {
                    String eName = allAnimals.get(i).getEnName().replace("_", " ");
                    if (item.getTitle().equals(eName)) {
                        int rawResId = ResUtils.getRawResAddressByName(BubblesActivity.this, "e_" + allAnimals.get(i).getEnName());
                        SoundHelper.getInstance().playTrack(rawResId, new OnPlayCompliteListener() {
                            @Override
                            public void onComplite() {

                            }
                        });
                    }
                }
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem item) {

            }
        });
    }

    private void getAllAnimalsAsOneList() {
        allAnimals = new ArrayList<>();
        allAnimals = DataController_.getInstance().getLearnModelAll();
    }

    BubblePickerAdapter adapter = new BubblePickerAdapter() {
        @Override
        public int getTotalCount() {
            return allAnimals.size();
        }

        @NotNull
        @Override
        public PickerItem getItem(int i) {
            PickerItem item = new PickerItem();
            String title = allAnimals.get(i).getEnName();
            title = title.replace("_", " ");
            item.setTitle(title);
//            item.setGradient(new BubbleGradient(colors.getColor((i * 2) % 8, 0),
//                    colors.getColor((i * 2) % 8 + 1, 0), BubbleGradient.VERTICAL));
            item.setGradient(new BubbleGradient(R.color.white,
                    generateRandomColor(), BubbleGradient.VERTICAL));
//            item.setTypeface(mediumTypeface);
            item.setTextColor(ContextCompat.getColor(BubblesActivity.this, android.R.color.white));
//            int drawableResourceId = getResources().getIdentifier(Constants.KEYS.C + allAnimals.get(i).getKey(), "drawable", getPackageName());
            int drawableResourceId = ResUtils.getDrawableResAddressByName(BubblesActivity.this, Constants.KEYS.C + allAnimals.get(i).getEnName());
            item.setBackgroundImage(ContextCompat.getDrawable(BubblesActivity.this, drawableResourceId));
            return item;
        }
    };

    private OnPlayCompliteListener onPlayCompliteListener = new OnPlayCompliteListener() {
        @Override
        public void onComplite() {

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        picker.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        picker.onPause();
    }


    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    createBubbles();
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(int i, int i1) {

    }

    private int generateRandomColor() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
}
