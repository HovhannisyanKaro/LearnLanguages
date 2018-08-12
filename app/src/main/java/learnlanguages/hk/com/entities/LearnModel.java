package learnlanguages.hk.com.entities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

import java.lang.ref.WeakReference;

import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.utils.GeneralMetods;
import learnlanguages.hk.com.utils.ResUtils;


/**
 * Created by Hovhannisyan.Karo on 24.03.2018.
 */


public class LearnModel {
    private static final String ARM = "arm_";
    private static final String EN = "e_";
    private static final String ANIMAL = "s_";
    private static final String IMAGE = "c_i_";

    /**
     * @param key  - base key for learnModel (title of learnModel), all other resource I'm going to get with this key.
     */
    private String key;
    private boolean hasBeen = false;

    public LearnModel(String key) {
        this.key = key;
    }

    public String getArmName(Context context) {
        return ResUtils.getStringResByKey(context, ARM + key);
    }

    public String getEnName() {
        return key;
    }

    public int getArmVoice(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            return ResUtils.getRawResAddressByName(context, ARM + key);
        } else {
            return 0;
        }
    }

    public int getEnVoice(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            return ResUtils.getRawResAddressByName(context, EN + key);
        } else return 0;
    }

    public int getAnimalVoice(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            return ResUtils.getRawResAddressByName(context, ANIMAL + key);
        } else return 0;

    }

    public int getImage(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            return ResUtils.getDrawableResAddressByName(context, IMAGE + key);
        } else return 0;
    }

    public Drawable getImageDrawable(Context context) {
        WeakReference<Context> weakReference = new WeakReference<>(context);
        if (weakReference.get() != null) {
            return ContextCompat.getDrawable(context, getImage(context));
        } else return null;

    }

    public boolean isHasBeen() {
        return hasBeen;
    }

    public void setHasBeen(boolean hasBeen) {
        this.hasBeen = hasBeen;
    }

    @Override
    public String toString() {
        return "LearnModel{" +
                "key='" + key + '\'' +
                '}';
    }
}
