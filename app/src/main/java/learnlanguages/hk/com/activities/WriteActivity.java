package learnlanguages.hk.com.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Animal;
import learnlanguages.hk.com.entities.WordView;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.SoundHelper;

public class WriteActivity extends AppCompatActivity {

    @BindView(R.id.iv_image_png)
    ImageView ivImagePng;
    @BindView(R.id.ll_words_container)
    LinearLayout llWordsContainer;
    @BindView(R.id.ll_answer)
    LinearLayout llAnswer;
    @BindView(R.id.ll_words_container2)
    LinearLayout llWordsContainer2;

    private Animal currAnimal;
    private ArrayList<Animal> allAnimals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getAllAnimalsAsOneList();
        initCurrentAnimal();
    }

    private void getAllAnimalsAsOneList() {
        allAnimals = new ArrayList<>();
        allAnimals.addAll(DataController.getInstance().getWhildAnimals());
        allAnimals.addAll(DataController.getInstance().getDomesticAnimals());
        allAnimals.addAll(DataController.getInstance().getBirdAnimals());
        allAnimals.addAll(DataController.getInstance().getAquaticAnimals());
    }

    private void getNotRepeatingAnimal() {
        ArrayList<Animal> data = allAnimals;
        do {
            currAnimal = data.get(new Random().nextInt(data.size() - 1));
        }
        while (currAnimal.isHasBeen());

        currAnimal.setHasBeen(true);
    }

    private void initCurrentAnimal() {
        llAnswer.removeAllViews();
        llWordsContainer.removeAllViews();
        llWordsContainer2.removeAllViews();

        getNotRepeatingAnimal();

        ivImagePng.setImageDrawable(currAnimal.getImage());
        SoundHelper.getInstance().playTrack(currAnimal.geteVoice(), onPlayCompliteListener);
        setWordViews(currAnimal);
        setAnswerLetters();
    }

    private void setWordViews(Animal currAnimal) {
        char[] nameWordsArr = currAnimal.geteName().toCharArray();

        Character[] answerVariants = new Character[nameWordsArr.length + (nameWordsArr.length / 3)];

        for (int i = 0; i < answerVariants.length; i++) {
            if (i < nameWordsArr.length) {
                answerVariants[i] = nameWordsArr[i];
            } else {
                char randomLetter = generateRandomLetter();
                for (int j = 0; j < nameWordsArr.length; j++) {
                    if (randomLetter == nameWordsArr[j]) {

                    } else {
                        answerVariants[i] = randomLetter;
                        break;
                    }
                }
            }
        }

        List<Character> chars = Arrays.asList(answerVariants);
        Collections.shuffle(chars);
        answerVariants = chars.toArray(answerVariants);

        int answerLength = answerVariants.length;
        for (int i = 0; i < answerLength; i++) {
            WordView wordView = new WordView(this);
            if (answerVariants[i] != '_') {
                wordView.setLetter(answerVariants[i]);
                wordView.setClickable(true);
                wordView.setOnLetterClickListener(onLetterClickListener);
                if (i > answerLength / 2) {
                    llWordsContainer2.addView(wordView);
                } else {
                    llWordsContainer.addView(wordView);
                }
            }
        }
    }

    private char generateRandomLetter() {
        return (char) ((new Random()).nextInt(26) + 'a');
    }

    @Override
    protected void onPause() {
        super.onPause();
        SoundHelper.getInstance().stopPlayer();
    }

    private void setAnswerLetters() {
        for (int i = 0; i < currAnimal.geteName().length(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 8, 8, 8);
            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setTextSize(24);
            if (currAnimal.geteName().charAt(i) == '_')
                tv.setText(" ");
            else
                tv.setText("_");

            tv.setTextColor(ContextCompat.getColor(this, R.color.write_answer_letter_color));
            llAnswer.addView(tv);
        }
    }

    private WordView.OnLetterClickListener onLetterClickListener = new WordView.OnLetterClickListener() {
        @Override
        public void onLetterClick(WordView wordView) {
            char currLetter = wordView.getLetter();
            boolean isVibrate = true;
            for (int i = 0; i < currAnimal.geteName().toCharArray().length; i++) {
                if (currLetter == currAnimal.geteName().toCharArray()[i]) {
                    ((TextView) llAnswer.getChildAt(i)).setText(String.valueOf(currLetter));
                    isVibrate = false;
                }
            }
            if (isVibrate) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
            }

            if (checkIfWin()) {
                ivImagePng.startAnimation(AnimationUtils.loadAnimation(WriteActivity.this, R.anim.anim_vibration));
                SoundHelper.getInstance().playTrack(currAnimal.getAnimalVoice(), onWinnerPlayCompleteListener);
            }
        }
    };

    private OnPlayCompliteListener onPlayCompliteListener = new OnPlayCompliteListener() {
        @Override
        public void onComplite() {

        }
    };

    private OnPlayCompliteListener onWinnerPlayCompleteListener = new OnPlayCompliteListener() {
        @Override
        public void onComplite() {
            initCurrentAnimal();
        }
    };

    private boolean checkIfWin() {
        boolean isWin = true;
        for (int i = 0; i < llAnswer.getChildCount(); i++) {
            if (((TextView) llAnswer.getChildAt(i)).getText().toString().equals("_")) {
                isWin = false;
                break;
            }
        }
        return isWin;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setWriteActivity(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());

    }
}
