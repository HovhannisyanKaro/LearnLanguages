package learnlanguages.hk.com.interfacies;

/**
 * Created by Hovhannisyan.Karo on 23.08.2017.
 */

public interface Constants {

    String BACKGROUND_BASE = "background_main_";
    String BACKGROUND_LEARN_BASE = "background_learn_";

    String BACKGROUND_CARTOON = "cartoon";
    String BACKGROUND_REAL_IMAGE = "real_image";
    String BACKGROUND_LEARN_CARTOON = "cartoon";
    String BACKGROUND_LEARN_AQUATICS = "real_image_aquatics";
    String BACKGROUND_LEARN_NOT_AQUATICS = "real_image_not_aquatics";

    String SOUND_VALUE = "sound_value";


    interface CATEGORY {
        int BIRDS = 1;
        int AQUATIC = 2;
        int WHILD = 3;
        int DOMESTIC = 4;
        int COLOR = 5;
    }

    interface KEYS {
        String RI = "i_";
        String C = "c_i_";
    }

    interface SOUND_TYPES {
        int SOUND = 1;
        int E_SOUND = 2;
        int P_SOUND = 3;
    }

    interface LEVELS {
        int EASY = 1;
        int MIDDLE = 2;
        int HARD = 3;
    }

    interface G_TYPES {
        int TWO = 2;
        int THREE = 3;
        int FOUR = 4;
        int FIVE = 5;
        int SIX = 6;
    }
}
