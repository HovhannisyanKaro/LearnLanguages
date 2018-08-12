package learnlanguages.hk.com.new_version.splash_screen;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import learnlanguages.hk.com.interfacies.OnInitFinishListener;

/**
 * Created by Hovhannisyan.Karo on 28.03.2018.
 */

public class SplashPresenter implements ISplashPresenter {
    private static final int MSG_KEY = 21;

    private ISplashView iSplashView;
    private SplashInteractor interactor;
    private OnInitFinishListener onInitFinishListener;
    private InitHandler initHandler;


    public SplashPresenter(ISplashView iSplashView) {
        this.iSplashView = iSplashView;
        interactor = new SplashInteractor();
        initHandler = new InitHandler();
    }

    @Override
    public void initLearnModelData(final Context context, OnInitFinishListener onInitFinishListener) {
        this.onInitFinishListener = onInitFinishListener;


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        interactor.generateLearnModelBird(context);
                        interactor.generateLearnModelDomestic(context);
                        interactor.generateLearnModelSea(context);
                        interactor.generateLearnModelWild(context);
                        interactor.generateLearnModelColors(context);
                        initHandler.obtainMessage(MSG_KEY).sendToTarget();
                    }
                }).start();
            }
        }, 2400);


    }

    private class InitHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_KEY) {
                onInitFinishListener.onDataInited();
            }
        }
    }
}
