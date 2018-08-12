package learnlanguages.hk.com.new_version.splash_screen;

import android.content.Context;

import java.util.ArrayList;

import learnlanguages.hk.com.entities.LearnModel;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.new_version.controllers.DataController_;

/**
 * Created by Hovhannisyan.Karo on 28.03.2018.
 */

public class SplashInteractor implements ISplashInteractor{

    @Override
    public void generateLearnModelWild(Context context) {
        String[]wildAnimals = context.getResources().getStringArray(R.array.real_animal_whild);
        ArrayList<LearnModel>learnModelWild = new ArrayList<>();
        for (String wildAnimal : wildAnimals) {
            learnModelWild.add(new LearnModel(wildAnimal));
        }
        DataController_.getInstance().setLearnModelWild(learnModelWild);
    }

    @Override
    public void generateLearnModelDomestic(Context context) {
        String[]wildAnimals = context.getResources().getStringArray(R.array.real_animal_domestic);
        ArrayList<LearnModel>learnModelWild = new ArrayList<>();
        for (String wildAnimal : wildAnimals) {
            learnModelWild.add(new LearnModel(wildAnimal));
        }
        DataController_.getInstance().setLearnModelDomestic(learnModelWild);
    }

    @Override
    public void generateLearnModelBird(Context context) {
        String[]wildAnimals = context.getResources().getStringArray(R.array.real_animal_birds);
        ArrayList<LearnModel>learnModelWild = new ArrayList<>();
        for (String wildAnimal : wildAnimals) {
            learnModelWild.add(new LearnModel(wildAnimal));
        }
        DataController_.getInstance().setLearnModelBirds(learnModelWild);
    }

    @Override
    public void generateLearnModelSea(Context context) {
        String[]wildAnimals = context.getResources().getStringArray(R.array.real_animal_sea);
        ArrayList<LearnModel>learnModelWild = new ArrayList<>();
        for (String wildAnimal : wildAnimals) {
            learnModelWild.add(new LearnModel(wildAnimal));
        }
        DataController_.getInstance().setLearnModelSea(learnModelWild);
    }

    @Override
    public void generateLearnModelColors(Context context) {
        String[]colors = context.getResources().getStringArray(R.array.colors);
        ArrayList<LearnModel>learnModelColors = new ArrayList<>();
        for (String color : colors) {
            learnModelColors.add(new LearnModel(color));
        }
        DataController_.getInstance().setLearnModeColors(learnModelColors);
    }
}
