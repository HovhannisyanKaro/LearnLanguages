package learnlanguages.hk.com.entities;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.utils.GeneralMetods;


/**
 * Created by Hovhannisyan.Karo on 25.08.2017.
 */

public class Animal {
    private String eName;
    private String pName;
    private Uri eVoice;
    private Uri pVoice;
    private Drawable cImage;
    private Drawable aImage;
    private Uri animalVoice;
    private boolean hasBeen;


    public Animal(String eName, String pName, Uri eVoice, Uri pVoice, Drawable cImage, Drawable aImage, Uri animalVoice) {
        this.eName = eName;
        this.pName = pName;
        this.eVoice = eVoice;
        this.pVoice = pVoice;
        this.cImage = cImage;
        this.aImage = aImage;
        this.animalVoice = animalVoice;
    }

    public String geteName() {
        return eName;
    }

    public String getpName() {
        return pName;
    }

    public Uri geteVoice() {
        return eVoice;
    }

    public Uri getpVoice() {
        return pVoice;
    }

    public Drawable getImage() {

            return GeneralMetods.getSingleImageFromDrawable(Constants.KEYS.C + eName).get();

    }

    public boolean isHasBeen() {
        return hasBeen;
    }

    public void setHasBeen(boolean hasBeen) {
        this.hasBeen = hasBeen;
    }

    public Uri getAnimalVoice() {
        return animalVoice;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "eName='" + eName + '\'' +
                ", pName='" + pName + '\'' +
                ", eVoice=" + eVoice +
                ", pVoice=" + pVoice +
                ", cImage=" + cImage +
                ", aImage=" + aImage +
                ", animalVoice=" + animalVoice +
                '}';
    }
}
