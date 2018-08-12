package learnlanguages.hk.com.controllers;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import learnlanguages.hk.com.activities.LearnActivity;
import learnlanguages.hk.com.activities.PhoneMainActivity;
import learnlanguages.hk.com.activities.PlayActivity;
import learnlanguages.hk.com.activities.WriteActivity;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.fragments.LearnFragment;
import learnlanguages.hk.com.fragments.LevelSelectionFragment;


/**
 * Created by Hovhannisyan.Karo on 23.08.2017.
 */

public class ViewController {

    private static ViewController dataController = null;
    private Activity contex;
    private FragmentManager fragmentManager;
    private LearnFragment learnFragment;
    private LevelSelectionFragment levelSelectionFragment;
    private PlayActivity playActivity;
    private WriteActivity writeActivity;
    private LearnActivity learnActivity;
    private PhoneMainActivity phoneMainActivity;

    private ViewController() {
    }

    public static ViewController getViewController() {
        if (dataController == null) {
            dataController = new ViewController();
        }
        return dataController;
    }

    public LevelSelectionFragment getLevelSelectionFragment() {
        return levelSelectionFragment;
    }

    public void setLevelSelectionFragment(LevelSelectionFragment levelSelectionFragment) {
        this.levelSelectionFragment = levelSelectionFragment;
    }

    public PlayActivity getPlayActivity() {
        return playActivity;
    }

    public void setPlayActivity(PlayActivity playActivity) {
        this.playActivity = playActivity;
    }

    public LearnActivity getLearnActivity() {
        return learnActivity;
    }

    public void setLearnActivity(LearnActivity learnActivity) {
        this.learnActivity = learnActivity;
    }

    public WriteActivity getWriteActivity() {
        return writeActivity;
    }

    public void setWriteActivity(WriteActivity writeActivity) {
        this.writeActivity = writeActivity;
    }

    public void setPhoneMainActivity(PhoneMainActivity phoneMainActivity) {
        this.phoneMainActivity = phoneMainActivity;
    }

    public PhoneMainActivity getPhoneMainActivity() {
        return phoneMainActivity;
    }

    public LearnFragment getLearnFragment() {
        return learnFragment;
    }

    public void setLearnFragment(LearnFragment learnFragment) {
        this.learnFragment = learnFragment;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public Activity getContex() {
        return contex;
    }

    public void setContex(Activity contex) {
        this.contex = contex;
    }


    public void addFragment(@IdRes int comtainerId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(comtainerId, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    public void removeAllFragments() {
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    public void resetViewController() {
        dataController = null;
    }

}
