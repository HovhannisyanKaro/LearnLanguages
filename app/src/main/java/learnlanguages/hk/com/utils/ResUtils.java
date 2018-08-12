package learnlanguages.hk.com.utils;

import android.content.Context;

import learnlanguages.hk.com.interfacies.Constants;

/**
 * Created by Hovhannisyan.Karo on 21.01.2018.
 */

public class ResUtils {

    public static String getStringResByKey(Context context, String key) {
        int text_id = context.getResources().getIdentifier(key, "string", context.getPackageName());
        return context.getResources().getString(text_id);
    }

    public static int getDrawableResAddressByName(Context context, String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public static int getRawResAddressByName(Context context, String name) {
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }
}
