package learnlanguages.hk.com.utils;

import android.app.Activity;
import android.view.View;

import learnlanguages.hk.com.interfacies.OnAnimEndAction;

/**
 * Created by Hovhannisyan.Karo on 21.02.2018.
 */

public class AnimUtils {

    public static void gupiButtonAnimate(final View view, final OnAnimEndAction endAction) {
        view.animate().scaleY(0.7f).scaleX(0.7f).withEndAction(new Runnable() {
            @Override
            public void run() {

                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleY(1f).scaleX(1f).setDuration(60).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                endAction.onEnd();
                            }
                        }).start();
                    }
                });
            }
        }).setDuration(60).start();
    }

    public static void gupiPhoneItemAnimate(final View view, final OnAnimEndAction endAction) {
        view.animate().scaleY(0.5f).scaleX(0.5f).withEndAction(new Runnable() {
            @Override
            public void run() {

                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleY(1f).scaleX(1f).setDuration(60).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                endAction.onEnd();
                            }
                        }).start();
                    }
                });
            }
        }).setDuration(60).start();
    }


    public static void gupiAnimalAnimate(final View view, final OnAnimEndAction endAction) {
        view.animate().scaleY(1.3f).scaleX(1.3f).withEndAction(new Runnable() {
            @Override
            public void run() {

                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleY(1f).scaleX(1f).setDuration(60).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                endAction.onEnd();
                            }
                        }).start();
                    }
                });
            }
        }).setDuration(60).start();
    }

    public static void gupiAnimalAnimate(final View view) {
        view.animate().scaleY(1.3f).scaleX(1.3f).withEndAction(new Runnable() {
            @Override
            public void run() {

                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.animate().scaleY(1f).scaleX(1f).setDuration(100).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                            }
                        }).start();
                    }
                });
            }
        }).setDuration(100).start();
    }

}
