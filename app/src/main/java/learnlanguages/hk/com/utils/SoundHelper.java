package learnlanguages.hk.com.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;


/**
 * Created by Hovhannisyan.Karo on 23.08.2017.
 */

public class SoundHelper {
    private static SoundHelper INSTANCE = null;
    private final Context context;
    private final String packageName;
    MediaPlayer mp;

    public static SoundHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SoundHelper();
        }
        return INSTANCE;
    }

    private SoundHelper() {
        this.context = ViewController.getViewController().getContex();
        this.packageName = context.getApplicationInfo().packageName;
    }

    public void playTrack(int trackId, final OnPlayCompliteListener onPlayCompliteListener) {
        DataController.getInstance().setCanTouch(false);
        stopPlayer();
        mp = MediaPlayer.create(context, trackId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                onPlayCompliteListener.onComplite();
                DataController.getInstance().setCanTouch(true);
            }
        });
        mp.start();
        mp.setLooping(false);
    }

    public void playTrack(int trackId, boolean isLooping, final OnPlayCompliteListener onPlayCompliteListener) {
        DataController.getInstance().setCanTouch(false);
        stopPlayer();
        mp = MediaPlayer.create(context, trackId);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                onPlayCompliteListener.onComplite();
                DataController.getInstance().setCanTouch(true);
            }
        });
        mp.start();
        mp.setLooping(isLooping);
    }

    public void playTrack(Uri trackId, final OnPlayCompliteListener onPlayCompliteListener) {
        DataController.getInstance().setCanTouch(false);
        stopPlayer();
        while (mp == null){
            mp = MediaPlayer.create(context, trackId);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    onPlayCompliteListener.onComplite();
                    DataController.getInstance().setCanTouch(true);
                }
            });
        }
        LogUtils.d("BAG", "trackId " + trackId.toString());
        LogUtils.d("BAG", "mediaPlayer " + mp.toString());

        mp.start();
        mp.setLooping(false);
    }

    public void pauseTrack(){
        if (mp != null){
            if (mp.isPlaying()){
                mp.pause();
            }
        }
    }

    public void mute(){
        if (mp!=null){
            mp.setVolume(0,0);
        }
    }

    public void unMute(){
        if (mp!=null){
            mp.setVolume(1,1);
        }
    }

    public void resumeTrack(){
        if (mp != null){
                mp.start();
        }
    }

    public void replayBackgorund(){
        if (mp == null){
            playTrack(R.raw.sound_background, true, new OnPlayCompliteListener() {
                @Override
                public void onComplite() {

                }
            });
        }
    }

    public void stopPlayer() {
        if (mp != null) {
            mp.reset();
            mp.release();
            mp = null;
        }
    }
}
