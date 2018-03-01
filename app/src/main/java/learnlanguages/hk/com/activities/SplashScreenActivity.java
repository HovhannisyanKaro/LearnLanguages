package learnlanguages.hk.com.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.luolc.emojirain.EmojiRainLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Animal;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.LogUtils;

import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.AQUATIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.BIRDS;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.DOMESTIC;
import static learnlanguages.hk.com.interfacies.Constants.CATEGORY.WHILD;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.E_SOUND;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.P_SOUND;
import static learnlanguages.hk.com.interfacies.Constants.SOUND_TYPES.SOUND;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.group_emoji_container)
    EmojiRainLayout groupEmojiContainer;

    @BindView(R.id.app_title)
    TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        init();
        initTextFont();
    }

    private void initTextFont() {
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/scribble_box.ttf");
        appTitle.setTypeface(myTypeface);
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    private void init() {
        final ArrayList<Uri> whildAnimalSound = getSoundFromRaw(WHILD, SOUND);
        final ArrayList<Uri> whildAnimalESound = getSoundFromRaw(WHILD, E_SOUND);
        final ArrayList<Uri> whildAnimalPSound = getSoundFromRaw(WHILD, P_SOUND);
        final String eNameWhild[] = getResources().getStringArray(R.array.real_animal_whild);
        final String pNameWhild[] = getResources().getStringArray(R.array.real_animal_whild_arm);

        final ArrayList<Uri> aquaticAnimalSound = getSoundFromRaw(AQUATIC, SOUND);
        final ArrayList<Uri> aquaticAnimalESound = getSoundFromRaw(AQUATIC, E_SOUND);
        final ArrayList<Uri> aquaticAnimalPSound = getSoundFromRaw(AQUATIC, P_SOUND);
        final String eNameAquatic[] = getResources().getStringArray(R.array.real_animal_sea);
        final String pNameAquatic[] = getResources().getStringArray(R.array.real_animal_sea_arm);

        final ArrayList<Uri> birdAnimalSound = getSoundFromRaw(BIRDS, SOUND);
        final ArrayList<Uri> birdAnimalESound = getSoundFromRaw(BIRDS, E_SOUND);
        final ArrayList<Uri> birdAnimalPSound = getSoundFromRaw(BIRDS, P_SOUND);
        final String eNameBirds[] = getResources().getStringArray(R.array.real_animal_birds);
        final String pNameBirds[] = getResources().getStringArray(R.array.real_animal_birds_arm);

        final ArrayList<Uri> domesticAnimalSound = getSoundFromRaw(DOMESTIC, SOUND);
        final ArrayList<Uri> domesticAnimalESound = getSoundFromRaw(DOMESTIC, E_SOUND);
        final ArrayList<Uri> domesticAnimalPSound = getSoundFromRaw(DOMESTIC, P_SOUND);
        final String eNamedomestic[] = getResources().getStringArray(R.array.real_animal_domestic);
        final String pNamedomestic[] = getResources().getStringArray(R.array.real_animal_domestic_arm);


        createAnimals(WHILD, eNameWhild, pNameWhild, whildAnimalSound, whildAnimalESound, whildAnimalPSound);
        createAnimals(AQUATIC, eNameAquatic, pNameAquatic, aquaticAnimalSound, aquaticAnimalESound, aquaticAnimalPSound);
        createAnimals(BIRDS, eNameBirds, pNameBirds, birdAnimalSound, birdAnimalESound, birdAnimalPSound);
        createAnimals(DOMESTIC, eNamedomestic, pNamedomestic, domesticAnimalSound, domesticAnimalESound, domesticAnimalPSound);
    }

    private void initEmoji(){
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

    private void createAnimals(final int category, final String eName[], final String pName[], final ArrayList<Uri> animalSound, final ArrayList<Uri> animalESound, final ArrayList<Uri> animalPSound) {
        final ArrayList<Animal> animals = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < eName.length; i++) {
                    Animal animal = new Animal(eName[i], pName[i], animalESound.get(i), animalPSound.get(i), null, null, animalSound.get(i));
                    LogUtils.d("HHHK_LOG", animal.toString());
                    animals.add(animal);
                }
                switch (category) {
                    case BIRDS:
                        DataController.getInstance().setBirdAnimals(animals);
                        break;
                    case AQUATIC:
                        DataController.getInstance().setAquaticAnimals(animals);
                        break;
                    case WHILD:
                        DataController.getInstance().setWhildAnimals(animals);
                        break;
                    case DOMESTIC:
                        DataController.getInstance().setDomesticAnimals(animals);
                        break;
                    default:
                        break;
                }
            }
        }).start();
    }

//

    private ArrayList<Uri> getSoundFromRaw(int animalType, int soundType) {
        ArrayList<Uri> sounds = new ArrayList<>();
        String[] names = null;
        String soundKey = "";

        switch (soundType) {
            case SOUND:
                soundKey = "s_";
                break;
            case E_SOUND:
                soundKey = "e_";
                break;
            case P_SOUND:
                soundKey = "arm_";
                break;
        }
        switch (animalType) {
            case BIRDS:
                names = getResources().getStringArray(R.array.real_animal_birds);
                break;
            case AQUATIC:
                names = getResources().getStringArray(R.array.real_animal_sea);
                break;
            case WHILD:
                names = getResources().getStringArray(R.array.real_animal_whild);
                break;
            case DOMESTIC:
                names = getResources().getStringArray(R.array.real_animal_domestic);
                break;
            default:
                break;
        }

        for (int i = 0; i < names.length; i++) {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + soundKey + names[i]);
            sounds.add(uri);
        }
        return sounds;
    }


}
