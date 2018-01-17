package learnlanguages.hk.com.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.entities.Animal;
import learnlanguages.hk.com.entities.WordView;
import learnlanguages.hk.com.learnlanguages.R;

public class WriteActivity extends AppCompatActivity {

    @BindView(R.id.iv_image_png)
    ImageView ivImagePng;
    @BindView(R.id.ll_words_container)
    LinearLayout llWordsContainer;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_write);
        ButterKnife.bind(this);

        ArrayList<Animal> data = DataController.getInstance().getDomesticAnimals();
        int randomNumber = new Random().nextInt(data.size() - 1);

        Animal currAnimal = data.get(randomNumber);
        ivImagePng.setImageDrawable(currAnimal.getImage());

        char[] nameWordsArr = currAnimal.geteName().toCharArray();

        for (int i = 0; i < nameWordsArr.length; i++) {
            WordView wordView = new WordView(this);
            wordView.setLetter(nameWordsArr[i]);
            llWordsContainer.addView(wordView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ViewController.getViewController().setContex(this);
        ViewController.getViewController().setWriteActivity(this);
        ViewController.getViewController().setFragmentManager(getSupportFragmentManager());

    }
}
