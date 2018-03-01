package learnlanguages.hk.com.utils;

import android.content.Context;

import learnlanguages.hk.com.interfacies.Constants;

/**
 * Created by Hovhannisyan.Karo on 21.01.2018.
 */

public class ResUtils {

    public static int getDrawableResAddressByName(Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static int getRawResAddressByName(Context context, String name){
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }
}
