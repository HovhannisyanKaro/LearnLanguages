package learnlanguages.hk.com.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.learnlanguages.R;


/**
 * Created by Hovhannisyan.Karo on 23.08.2017.
 */

public class GeneralMetods {

    public static WeakReference<Drawable> getSingleImageFromDrawable(String imageRes) {
        Context context = ViewController.getViewController().getContex();
        Drawable drawable = context.getResources().getDrawable(context.getResources()
                .getIdentifier(imageRes, "drawable", context.getPackageName()));

        return new WeakReference<Drawable>(drawable);
    }

    public static ArrayList<Drawable> getCartoonImagesByCategory() {
        Context context = ViewController.getViewController().getContex();
        ArrayList<Drawable> images = new ArrayList<>();
        String[] aquaticNames = context.getResources().getStringArray(R.array.real_animal_whild);

        for (int i = 0; i < aquaticNames.length; i++) {
//            images.add(getSingleImageFromDrawable(Constants.KEYS.RI + aquaticNames[i]));
        }
        return images;
    }

    public static ArrayList<Drawable> getRealImagesByCategory(int category) {
        Context context = ViewController.getViewController().getContex();

        String[] names = null;

        switch (category) {
            case Constants.CATEGORY.BIRDS:
                names = context.getResources().getStringArray(R.array.real_animal_birds);
                break;
            case Constants.CATEGORY.AQUATIC:
                names = context.getResources().getStringArray(R.array.real_animal_sea);
                break;
            case Constants.CATEGORY.WHILD:
                names = context.getResources().getStringArray(R.array.real_animal_whild);

                break;
            case Constants.CATEGORY.DOMESTIC:
                names = context.getResources().getStringArray(R.array.real_animal_domestic);

                break;
            default:
                break;
        }
        ArrayList<Drawable> images = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            LogUtils.d("MYYYY", names[i]);
            images.add(getSingleImageFromDrawable(Constants.KEYS.RI + names[i]).get());

        }
        return images;
    }

    public static ArrayList<Drawable> getCartoonImagesByCategory(int category) {
        Context context = ViewController.getViewController().getContex();

        String[] names = null;

        switch (category) {
            case Constants.CATEGORY.BIRDS:
                names = context.getResources().getStringArray(R.array.real_animal_birds);
                break;
            case Constants.CATEGORY.AQUATIC:
                names = context.getResources().getStringArray(R.array.real_animal_sea);
                break;
            case Constants.CATEGORY.WHILD:
                names = context.getResources().getStringArray(R.array.real_animal_whild);

                break;
            case Constants.CATEGORY.DOMESTIC:
                names = context.getResources().getStringArray(R.array.real_animal_domestic);

                break;
            default:
                break;
        }
        ArrayList<Drawable> images = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            LogUtils.d("MYYYY", names[i]);
            images.add(getSingleImageFromDrawable(Constants.KEYS.C + names[i]).get());

        }
        return images;
    }

    public static StateListDrawable makeSelector() {
        int color = Color.WHITE;
        StateListDrawable res = new StateListDrawable();
        res.setExitFadeDuration(800);
        res.setAlpha(20);
        res.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(color));
        res.addState(new int[]{}, new ColorDrawable(Color.TRANSPARENT));
        return res;
    }
}
