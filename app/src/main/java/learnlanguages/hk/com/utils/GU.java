package learnlanguages.hk.com.utils;

/**
 * Created by Hovhannisyan.Karo on 26.03.2018.
 */

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import learnlanguages.hk.com.interfacies.OnUriLoadListener;

/**
 * GlideUtils with Firebase
 */
public class GU {

    private static final String IMAGES_BASE_URL = "gupi_app/images/learn/";
    private static final String SOUND_ANIMAL_VOICE_BASE_URL = "gupi_app/sounds/animal_sounds/";

    public static void loadImage(final ImageView iv, String imageName) {
        FirebaseStorage.getInstance().getReference().child(IMAGES_BASE_URL + "c_i_" + imageName + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(iv.getContext()).load(uri).into(iv);
            }
        });
    }

    public static void loadSound(String animalname, final OnUriLoadListener onUriLoadListener) {
        FirebaseStorage.getInstance().getReference().child(SOUND_ANIMAL_VOICE_BASE_URL + "s_" + animalname + ".mp3").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                onUriLoadListener.onUriLoaded(uri);
            }
        });
    }
}
