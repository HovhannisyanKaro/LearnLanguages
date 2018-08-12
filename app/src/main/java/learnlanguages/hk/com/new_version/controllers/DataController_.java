package learnlanguages.hk.com.new_version.controllers;

import java.util.ArrayList;

import learnlanguages.hk.com.entities.LearnModel;

/**
 * Created by Hovhannisyan.Karo on 28.03.2018.
 */

public class DataController_ {
    private static final DataController_ ourInstance = new DataController_();
    private boolean canTouch = true;
    private int level;
    private int category;

    private ArrayList<LearnModel> learnModelWild = new ArrayList<>();
    private ArrayList<LearnModel> learnModelDomestic = new ArrayList<>();
    private ArrayList<LearnModel> learnModelSea = new ArrayList<>();
    private ArrayList<LearnModel> learnModelBirds = new ArrayList<>();
    private ArrayList<LearnModel> learnModeColors = new ArrayList<>();
    private ArrayList<LearnModel> learnModelAll = new ArrayList<>();

    public static DataController_ getInstance() {
        return ourInstance;
    }

    private DataController_() {
    }

    public ArrayList<LearnModel> getLearnModelWild() {
        return learnModelWild;
    }

    public void setLearnModelWild(ArrayList<LearnModel> learnModelWild) {
        this.learnModelWild = learnModelWild;
        learnModelAll.addAll(learnModelWild);
    }

    public ArrayList<LearnModel> getLearnModelDomestic() {
        return learnModelDomestic;
    }

    public void setLearnModelDomestic(ArrayList<LearnModel> learnModelDomestic) {
        this.learnModelDomestic = learnModelDomestic;
        learnModelAll.addAll(learnModelDomestic);

    }

    public ArrayList<LearnModel> getLearnModelSea() {
        return learnModelSea;
    }

    public void setLearnModelSea(ArrayList<LearnModel> learnModelSea) {
        this.learnModelSea = learnModelSea;
        learnModelAll.addAll(learnModelSea);

    }

    public ArrayList<LearnModel> getLearnModelBirds() {
        return learnModelBirds;
    }

    public ArrayList<LearnModel> getLearnModelColors() {
        return learnModeColors;
    }

    public void setLearnModelBirds(ArrayList<LearnModel> learnModelBirds) {
        this.learnModelBirds = learnModelBirds;
        learnModelAll.addAll(learnModelBirds);
    }

    public void setLearnModeColors(ArrayList<LearnModel> learnModeColors) {
        this.learnModeColors = learnModeColors;
        learnModelAll.addAll(learnModeColors);
    }


    public ArrayList<LearnModel> getLearnModelAll() {
        return learnModelAll;
    }

    public boolean isCanTouch() {
        return canTouch;
    }

    public void setCanTouch(boolean canTouch) {
        this.canTouch = canTouch;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
