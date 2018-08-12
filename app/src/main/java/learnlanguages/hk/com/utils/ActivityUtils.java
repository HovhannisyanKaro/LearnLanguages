package learnlanguages.hk.com.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Hovhannisyan.Karo on 28.03.2018.
 */

public class ActivityUtils {

    public static void setFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
