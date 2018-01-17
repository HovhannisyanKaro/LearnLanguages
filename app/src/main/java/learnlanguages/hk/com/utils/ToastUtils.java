package learnlanguages.hk.com.utils;

import android.widget.Toast;

import learnlanguages.hk.com.controllers.ViewController;


/**
 * Created by Hovhannisyan.Karo on 25.02.2017.
 */

public class ToastUtils {
    private static final boolean isDebug = true;

    public static void t(String message) {
        if (isDebug)
            Toast.makeText(ViewController.getViewController().getContex(), message, Toast.LENGTH_SHORT).show();
    }
}
