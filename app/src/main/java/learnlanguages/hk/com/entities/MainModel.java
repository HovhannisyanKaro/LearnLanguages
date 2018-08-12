package learnlanguages.hk.com.entities;

import java.util.ArrayList;

/**
 * Created by Hovhannisyan.Karo on 24.03.2018.
 */

public class MainModel {
    private String categoryName;
    private ArrayList<LearnModel> animals = new ArrayList<>();

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<LearnModel> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<LearnModel> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "MainModel{" +
                "categoryName='" + categoryName + '\'' +
                ", animals=" + animals +
                '}';
    }
}
