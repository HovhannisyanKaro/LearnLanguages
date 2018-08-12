package learnlanguages.hk.com.controllers;

import java.util.ArrayList;

import learnlanguages.hk.com.entities.MainModel;

/**
 * Created by Hovhannisyan.Karo on 26.03.2018.
 */

/**
 * DC2
 */
public class DC2 {
    private static final DC2 ourInstance = new DC2();

    private ArrayList<String> categoryNames = new ArrayList<>();
    private ArrayList<MainModel> allData;

    public static DC2 gI(/** gI*/) {
        return ourInstance;
    }

    private DC2() {
    }

    public void addCategoryName(String categoryTitle) {
        categoryNames.add(categoryTitle);
    }

    public ArrayList<String> getCategoryNames() {
        return categoryNames;
    }

    public ArrayList<MainModel> getAllData() {
        return allData;
    }

    public void addMainModel(MainModel mainModel) {
        if (allData == null)
            allData = new ArrayList<>();
        allData.add(mainModel);
    }

}
