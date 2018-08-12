package learnlanguages.hk.com.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.luolc.emojirain.EmojiRainLayout;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.DC2;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.LearnModel;
import learnlanguages.hk.com.entities.MainModel;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.ActivityUtils;
import learnlanguages.hk.com.utils.LogUtils;

import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.AQUATIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.BIRDS;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.DOMESTIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.WHILD;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.E_SOUND;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.P_SOUND;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.SOUND;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String rootUrlLearn = "https://hkfirebase.firebaseio.com/gupi_app/learn_categories";
    private static final String rootImagesUrl = "gs://hkfirebase.appspot.com/gupi_app/images/learn";

    @BindView(R.id.group_emoji_container)
    EmojiRainLayout groupEmojiContainer;

    @BindView(R.id.app_title)
    TextView appTitle;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.setFullScreen(this);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        }, 5000);
        ButterKnife.bind(this);

        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());
        initEmoji();

//        database = FirebaseDatabase.getInstance();

        init();
        initTextFont();
    }


    private void init() {
//        readRootFromFireBAse();

//        final ArrayList<Uri> whildAnimalSound = getSoundFromRaw(WHILD, SOUND);
//        final ArrayList<Uri> whildAnimalESound = getSoundFromRaw(WHILD, E_SOUND);
//        final ArrayList<Uri> whildAnimalPSound = getSoundFromRaw(WHILD, P_SOUND);
//        final String eNameWhild[] = getResources().getStringArray(R.array.real_animal_whild);
//        final String pNameWhild[] = getResources().getStringArray(R.array.real_animal_whild_arm);
//
//        final ArrayList<Uri> aquaticAnimalSound = getSoundFromRaw(AQUATIC, SOUND);
//        final ArrayList<Uri> aquaticAnimalESound = getSoundFromRaw(AQUATIC, E_SOUND);
//        final ArrayList<Uri> aquaticAnimalPSound = getSoundFromRaw(AQUATIC, P_SOUND);
//        final String eNameAquatic[] = getResources().getStringArray(R.array.real_animal_sea);
//        final String pNameAquatic[] = getResources().getStringArray(R.array.real_animal_sea_arm);
//
//        final ArrayList<Uri> birdAnimalSound = getSoundFromRaw(BIRDS, SOUND);
//        final ArrayList<Uri> birdAnimalESound = getSoundFromRaw(BIRDS, E_SOUND);
//        final ArrayList<Uri> birdAnimalPSound = getSoundFromRaw(BIRDS, P_SOUND);
//        final String eNameBirds[] = getResources().getStringArray(R.array.real_animal_birds);
//        final String pNameBirds[] = getResources().getStringArray(R.array.real_animal_birds_arm);
//
//        final ArrayList<Uri> domesticAnimalSound = getSoundFromRaw(DOMESTIC, SOUND);
//        final ArrayList<Uri> domesticAnimalESound = getSoundFromRaw(DOMESTIC, E_SOUND);
//        final ArrayList<Uri> domesticAnimalPSound = getSoundFromRaw(DOMESTIC, P_SOUND);
//        final String eNamedomestic[] = getResources().getStringArray(R.array.real_animal_domestic);
//        final String pNamedomestic[] = getResources().getStringArray(R.array.real_animal_domestic_arm);
//
//
//        createAnimals(WHILD, eNameWhild, pNameWhild, whildAnimalSound, whildAnimalESound, whildAnimalPSound);
//        createAnimals(AQUATIC, eNameAquatic, pNameAquatic, aquaticAnimalSound, aquaticAnimalESound, aquaticAnimalPSound);
//        createAnimals(BIRDS, eNameBirds, pNameBirds, birdAnimalSound, birdAnimalESound, birdAnimalPSound);
//        createAnimals(DOMESTIC, eNamedomestic, pNamedomestic, domesticAnimalSound, domesticAnimalESound, domesticAnimalPSound);
    }

    private void initTextFont() {
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/scribble_box.ttf");
        appTitle.setTypeface(myTypeface);
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }


    private void readRootFromFireBAse() {
        // Read from the database
        myRef = database.getReferenceFromUrl(rootUrlLearn);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                if (map != null) {
                    for (String key : map.keySet()) {
                        DC2.gI().addCategoryName(key);
                    }

                    readAnimalsFromFireBaseByName();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                LogUtils.d("Failed to read value. " + error.toException());
            }
        });
    }

    private void readAnimalsFromFireBaseByName() {
        if (DC2.gI().getCategoryNames() != null && !DC2.gI().getCategoryNames().isEmpty()) {
            for (int i = 0; i < DC2.gI().getCategoryNames().size(); i++) {
                DatabaseReference myRef = database.getReferenceFromUrl(rootUrlLearn + "/" + DC2.gI().getCategoryNames().get(i));

                final int finalI1 = i;
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                        if (map != null) {
                            MainModel mainModel = new MainModel();
                            mainModel.setCategoryName(DC2.gI().getCategoryNames().get(finalI1));
                            ArrayList<LearnModel> learnModels = new ArrayList<>();
//                            for (String key : map.keySet()) {
//                                final LearnModel learnModel = new LearnModel();
//                                learnModel.setKey(key);
//                                GU.loadSound(key, new OnUriLoadListener() {
//                                    @Override
//                                    public void onUriLoaded(Uri uri) {
//                                        learnModel.setAnimalVoice(uri);
//                                    }
//                                });
//                                learnModels.add(learnModel);
//                            }
                            mainModel.setAnimals(learnModels);
                            mainModel.toString();
                            DC2.gI().addMainModel(mainModel);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        LogUtils.d("Failed to read value. " + error.toException());
                    }
                });
            }


        } else {
            // TODO: 26.03.2018 show no internet dialog
        }


    }

    private void initEmoji() {
        groupEmojiContainer.addEmoji(R.drawable.c_i_shark);
        groupEmojiContainer.addEmoji(R.drawable.c_i_bat);
        groupEmojiContainer.addEmoji(R.drawable.c_i_pelican);
        groupEmojiContainer.addEmoji(R.drawable.c_i_canary);
        groupEmojiContainer.addEmoji(R.drawable.c_i_dog);
        groupEmojiContainer.addEmoji(R.drawable.c_i_yellow_fish);
        groupEmojiContainer.addEmoji(R.drawable.c_i_zebra);
        groupEmojiContainer.addEmoji(R.drawable.c_i_chimpanzee);
        groupEmojiContainer.addEmoji(R.drawable.c_i_elephant);

        groupEmojiContainer.setPer(5);

        // set total duration in milliseconds, default 8000
        groupEmojiContainer.setDuration(4700);

        // set average drop duration in milliseconds, default 2400
        groupEmojiContainer.setDropDuration(2400);

        // set drop frequency in milliseconds, default 500
        groupEmojiContainer.setDropFrequency(500);
        groupEmojiContainer.startDropping();
    }

}
