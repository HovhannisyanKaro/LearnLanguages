package learnlanguages.hk.com.entities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.interfacies.OnPlayCompliteListener;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.new_version.controllers.DataController_;
import learnlanguages.hk.com.utils.SoundHelper;

/**
 * Created by Hovhannisyan.Karo on 03.09.2017.
 */

public class Game extends RelativeLayout {

    @BindView(R.id.iv_t_2_0)
    ImageView ivT20;
    @BindView(R.id.iv_t_2_1)
    ImageView ivT21;
    @BindView(R.id.ll_type_two)
    LinearLayout llTypeTwo;
    @BindView(R.id.iv_t_3_0)
    ImageView ivT30;
    @BindView(R.id.iv_t_3_1)
    ImageView ivT31;
    @BindView(R.id.iv_t_3_2)
    ImageView ivT32;
    @BindView(R.id.ll_type_three)
    LinearLayout llTypeThree;
    @BindView(R.id.iv_t_4_0)
    ImageView ivT40;
    @BindView(R.id.iv_t_4_1)
    ImageView ivT41;
    @BindView(R.id.iv_t_4_2)
    ImageView ivT42;
    @BindView(R.id.iv_t_4_3)
    ImageView ivT43;
    @BindView(R.id.ll_type_four)
    LinearLayout llTypeFour;
    @BindView(R.id.iv_t_5_0)
    ImageView ivT50;
    @BindView(R.id.iv_t_5_1)
    ImageView ivT51;
    @BindView(R.id.iv_t_5_2)
    ImageView ivT52;
    @BindView(R.id.iv_t_5_3)
    ImageView ivT53;
    @BindView(R.id.iv_t_5_4)
    ImageView ivT54;
    @BindView(R.id.ll_type_five)
    LinearLayout llTypeFive;
    @BindView(R.id.iv_t_6_0)
    ImageView ivT60;
    @BindView(R.id.iv_t_6_1)
    ImageView ivT61;
    @BindView(R.id.iv_t_6_2)
    ImageView ivT62;
    @BindView(R.id.iv_t_6_3)
    ImageView ivT63;
    @BindView(R.id.iv_t_6_4)
    ImageView ivT64;
    @BindView(R.id.iv_t_6_5)
    ImageView ivT65;
    @BindView(R.id.ll_type_six)
    LinearLayout llTypeSix;
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.btn_listen)
    Button btnListen;
    @BindView(R.id.ll_stars)
    LinearLayout llStars;
    @BindView(R.id.iv_star_1)
    ImageView ivStar1;
    @BindView(R.id.iv_star_2)
    ImageView ivStar2;
    @BindView(R.id.iv_star_3)
    ImageView ivStar3;
    @BindView(R.id.iv_star_4)
    ImageView ivStar4;
    @BindView(R.id.iv_star_5)
    ImageView ivStar5;
    @BindView(R.id.ib_skip)
    ImageButton ibSkip;
    @BindView(R.id.ib_e_name)
    ImageButton ibEName;
    @BindView(R.id.ib_p_name)
    ImageButton ibPName;
    @BindView(R.id.ib_animal_sound)
    ImageButton ibAnimalSound;
    private LayoutInflater inflater;
    private Context context;
    Unbinder unbinder;

    private Activity activity;

    private ImageView[] ivTypes2;
    private ImageView[] ivTypes3;
    private ImageView[] ivTypes4;
    private ImageView[] ivTypes5;
    private ImageView[] ivTypes6;
    private ImageView[] ivStars;

    private LinearLayout[] layouts;

    private ArrayList<LearnModel> allAnimals;
    private ArrayList<LearnModel> rightAnswers;
    private LearnModel rightAnimal = null;
    private int gameType = 2;

    private int levelsCountMiddle = 0;
    private int levelsCountHard = 0;

    public Game(Context context) {
        super(context);
        init(context);
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_game, this);
        unbinder = ButterKnife.bind(this, view);
        rightAnswers = new ArrayList<>();
        setArraysOfImageViews();
        getAllAnimalsAsOneList();
        initListeners();
        logicOfGame();
        setVisibilityOFStars();
    }

    private void initListeners() {
        btnTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        for (int i = 0; i < ivTypes2.length; i++) {
            final int finalI = i;
            ivTypes2[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataController_.getInstance().isCanTouch())
                        checkAnswer(ivTypes2[finalI].getDrawable());
                }
            });


        }

        for (int i = 0; i < ivTypes3.length; i++) {
            final int finalI = i;
            ivTypes3[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataController_.getInstance().isCanTouch())
                        checkAnswer(ivTypes3[finalI].getDrawable());

                }
            });


        }

        for (int i = 0; i < ivTypes4.length; i++) {
            final int finalI = i;
            ivTypes4[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataController_.getInstance().isCanTouch())
                        checkAnswer(ivTypes4[finalI].getDrawable());

                }
            });


        }

        for (int i = 0; i < ivTypes5.length; i++) {
            final int finalI = i;

            ivTypes5[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataController_.getInstance().isCanTouch())
                        checkAnswer(ivTypes5[finalI].getDrawable());

                }
            });


        }

        for (int i = 0; i < ivTypes6.length; i++) {
            final int finalI = i;

            ivTypes6[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (DataController_.getInstance().isCanTouch())
                        checkAnswer(ivTypes6[finalI].getDrawable());
                }
            });


        }
    }

    private void setArraysOfImageViews() {
        ivTypes2 = new ImageView[]{ivT20, ivT21};
        ivTypes3 = new ImageView[]{ivT30, ivT31, ivT32};
        ivTypes4 = new ImageView[]{ivT40, ivT41, ivT42, ivT43};
        ivTypes5 = new ImageView[]{ivT50, ivT51, ivT52, ivT53, ivT54};
        ivTypes6 = new ImageView[]{ivT60, ivT61, ivT62, ivT63, ivT64, ivT65};

        layouts = new LinearLayout[]{llTypeTwo, llTypeThree, llTypeFour, llTypeFive, llTypeSix};
        ivStars = new ImageView[]{ivStar1, ivStar2, ivStar3, ivStar4, ivStar5};
    }

    private void setVisibilityOfType(int type) {
        type -= 2;
        for (int i = 0; i < layouts.length; i++) {
            if (i == type) {
                layouts[i].setVisibility(VISIBLE);
            } else {
                layouts[i].setVisibility(GONE);
            }
        }
    }

    private void setVisibilityOFStars() {
        int level = DataController_.getInstance().getLevel();
        if (level == Constants.LEVELS.EASY) {
            ivStars[0].setVisibility(VISIBLE);
        } else if (level == Constants.LEVELS.MIDDLE) {
            for (int i = 0; i < 3; i++) {
                ivStars[i].setVisibility(VISIBLE);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                ivStars[i].setVisibility(VISIBLE);
            }
        }

    }

    private void getAllAnimalsAsOneList() {
        allAnimals = new ArrayList<>();
        allAnimals = DataController_.getInstance().getLearnModelAll();
    }

    private void logicOfGame() {
        if (gameType == Constants.G_TYPES.TWO) {
            logic(Constants.G_TYPES.TWO);
        } else if (gameType == Constants.G_TYPES.THREE) {
            logic(Constants.G_TYPES.THREE);
        } else if (gameType == Constants.G_TYPES.FOUR) {
            logic(Constants.G_TYPES.FOUR);
        } else if (gameType == Constants.G_TYPES.FIVE) {
            logic(Constants.G_TYPES.FIVE);
        } else {
            logic(Constants.G_TYPES.SIX);
        }
    }

    private void logic(int type) {
        setVisibilityOfType(type);
        int rightAnswerIndex = getRandomInt(type);
        ArrayList<LearnModel> removedAnimals = null;
        removedAnimals = new ArrayList<>();
        removedAnimals.addAll(allAnimals);
        ImageView[] arrayOfImageView = null;
        switch (type) {
            case Constants.G_TYPES.TWO:
                arrayOfImageView = ivTypes2;
                break;
            case Constants.G_TYPES.THREE:
                arrayOfImageView = ivTypes3;
                break;
            case Constants.G_TYPES.FOUR:
                arrayOfImageView = ivTypes4;
                break;
            case Constants.G_TYPES.FIVE:
                arrayOfImageView = ivTypes5;
                break;
            case Constants.G_TYPES.SIX:
                arrayOfImageView = ivTypes6;
                break;
        }
        for (int i = 0; i < type; i++) {
            LearnModel animal = removedAnimals.get(getRandomInt(removedAnimals.size()));

            arrayOfImageView[i].setImageResource(animal.getImage(context));

            if (i == rightAnswerIndex) {
                rightAnimal = animal;
            }
            removedAnimals.remove(animal);
            removedAnimals.trimToSize();
        }
        SoundHelper.getInstance().playTrack(rightAnimal.getEnVoice(context), new OnPlayCompliteListener() {
            @Override
            public void onComplite() {

            }
        });
    }

    private void checkAnswer(Drawable answeredDrawable) {
        int gameLevel = DataController_.getInstance().getLevel();
        if (gameLevel == Constants.LEVELS.EASY) {
            if (answeredDrawable.getConstantState().equals(rightAnimal.getImageDrawable(context).getConstantState())) {
                if (gameType < 6) {
                    ivStars[0].setImageResource(R.drawable.star_win);
                    SoundHelper.getInstance().playTrack(R.raw.action_sound_level_victory, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {

//                    showRightAnswerDialog();
                            inPlaceOfDialog();
                            gameType++;
                        }
                    });

                } else if (gameType == 6) {
                    showWinDialog();
                    SoundHelper.getInstance().playTrack(R.raw.action_sound_game_victory, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {

                        }
                    });
                }
            } else {
                if (gameType > 2) {
                    gameType--;
                }
                SoundHelper.getInstance().playTrack(R.raw.action_sound_skip, new OnPlayCompliteListener() {
                    @Override
                    public void onComplite() {
                        logicOfGame();
                    }
                });

            }
        } else if (gameLevel == Constants.LEVELS.MIDDLE) {
            if (answeredDrawable.getConstantState().equals(rightAnimal.getImageDrawable(context).getConstantState())) {
                if (gameType < 6) {
                    int sound = R.raw.action_sound_right_answer;
                    if (levelsCountMiddle == 2) {
                        sound = R.raw.action_sound_level_victory;
                    }
                    SoundHelper.getInstance().playTrack(sound, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {
                            if (levelsCountMiddle < 2) {
                                levelsCountMiddle++;
                                logicOfGame();
                            } else if (levelsCountMiddle == 2) {
                                gameType++;
                                levelsCountMiddle = 0;
//                        showRightAnswerDialog();
                                inPlaceOfDialog();
                            }
                            for (int i = 0; i < ivStars.length; i++) {
                                if (i < levelsCountMiddle) {
                                    ivStars[i].setImageResource(R.drawable.star_win);
                                } else {
                                    ivStars[i].setImageResource(R.drawable.star);
                                }
                            }
                        }
                    });

                } else if (gameType == 6) {
                    int sound = R.raw.action_sound_right_answer;
                    if (levelsCountMiddle == 2) {
                        sound = R.raw.action_sound_game_victory;
                        showWinDialog();
                    }
                    SoundHelper.getInstance().playTrack(sound, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {
                            if (levelsCountMiddle < 2) {
                                levelsCountMiddle++;
                                logicOfGame();
                            }

                            for (int i = 0; i < ivStars.length; i++) {
                                if (i < levelsCountMiddle) {
                                    ivStars[i].setImageResource(R.drawable.star_win);
                                } else {
                                    ivStars[i].setImageResource(R.drawable.star);
                                }
                            }
                        }
                    });

                }
            } else {
                SoundHelper.getInstance().playTrack(R.raw.action_sound_skip, new OnPlayCompliteListener() {
                    @Override
                    public void onComplite() {
                        if (levelsCountMiddle > 0) {
                            levelsCountMiddle--;
                        } else if (levelsCountMiddle == 0) {
                            if (gameType > 2) {
                                gameType--;
                            }
                        }
                        logicOfGame();

                        for (int i = 0; i < ivStars.length; i++) {
                            if (i < levelsCountMiddle) {
                                ivStars[i].setImageResource(R.drawable.star_win);
                            } else {
                                ivStars[i].setImageResource(R.drawable.star);
                            }
                        }
                    }
                });
            }
        } else {
            if (answeredDrawable.getConstantState().equals(rightAnimal.getImageDrawable(context).getConstantState())) {
                if (gameType < 6) {
                    int sound = R.raw.action_sound_right_answer;
                    if (levelsCountHard == 4) {
                        sound = R.raw.action_sound_level_victory;
                    }

                    SoundHelper.getInstance().playTrack(sound, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {
                            if (levelsCountHard < 4) {
                                levelsCountHard++;
                                logicOfGame();
                            } else if (levelsCountHard == 4) {
                                gameType++;
                                levelsCountHard = 0;
//                        showRightAnswerDialog();
                                inPlaceOfDialog();
                            }

                            for (int i = 0; i < ivStars.length; i++) {
                                if (i < levelsCountHard) {
                                    ivStars[i].setImageResource(R.drawable.star_win);
                                } else {
                                    ivStars[i].setImageResource(R.drawable.star);
                                }
                            }
                        }
                    });

                } else if (gameType == 6) {
                    int sound = R.raw.action_sound_right_answer;
                    if (levelsCountHard == 4) {
                        sound = R.raw.action_sound_game_victory;
                        showWinDialog();
                    }
                    SoundHelper.getInstance().playTrack(sound, new OnPlayCompliteListener() {
                        @Override
                        public void onComplite() {
                            if (levelsCountHard < 4) {
                                levelsCountHard++;
                                logicOfGame();

                                for (int i = 0; i < ivStars.length; i++) {
                                    if (i < levelsCountHard) {
                                        ivStars[i].setImageResource(R.drawable.star_win);
                                    } else {
                                        ivStars[i].setImageResource(R.drawable.star);
                                    }
                                }
                            }
                        }
                    });

                }
            } else {
                SoundHelper.getInstance().playTrack(R.raw.action_sound_skip, new OnPlayCompliteListener() {
                    @Override
                    public void onComplite() {
                        if (levelsCountHard > 0) {
                            levelsCountHard--;
                        } else if (levelsCountHard == 0) {
                            if (gameType > 2) {
                                gameType--;
                            }
                        }
                        logicOfGame();

                        for (int i = 0; i < ivStars.length; i++) {
                            if (i < levelsCountHard) {
                                ivStars[i].setImageResource(R.drawable.star_win);
                            } else {
                                ivStars[i].setImageResource(R.drawable.star);
                            }
                        }
                    }
                });

            }
        }
    }

    private void showWinDialog() {
        Context context = ViewController.getViewController().getContex();
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_win);

        ImageButton replay = (ImageButton) dialog.findViewById(R.id.iv_replay);
        ImageButton exit = (ImageButton) dialog.findViewById(R.id.iv_close);

        replay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                dialog.dismiss();
            }
        });

        exit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewController.getViewController().removeAllFragments();
                getActivity().finish();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void inPlaceOfDialog() {
        final int level = DataController_.getInstance().getLevel();

        logicOfGame();
        if (level == Constants.LEVELS.EASY) {
            ivStars[0].setImageResource(R.drawable.star);
        }

    }

    private void resetGame() {
        gameType = 2;
        levelsCountMiddle = 0;
        levelsCountHard = 0;
        logicOfGame();
    }


    private int getRandomInt(int length) {
        return new Random().nextInt(length);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_skip, R.id.btn_listen, R.id.ib_skip, R.id.ib_e_name, R.id.ib_p_name, R.id.ib_animal_sound})
    public void onViewClicked(final View view) {
        boolean canTouch = DataController_.getInstance().isCanTouch();
        switch (view.getId()) {
            case R.id.btn_skip:
                if (canTouch)
                    logicOfGame();
                break;
            case R.id.btn_listen:
                if (canTouch) {
                    view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate().scaleY(1f).scaleX(1f).start();
                                    SoundHelper.getInstance().playTrack(rightAnimal.getAnimalVoice(context), new OnPlayCompliteListener() {
                                        @Override
                                        public void onComplite() {

                                        }
                                    });
                                }
                            });
                        }
                    }).setDuration(150).start();

                }
                break;
            case R.id.ib_skip:
                if (canTouch) {
                    view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate().scaleY(1f).scaleX(1f).start();
                                    SoundHelper.getInstance().playTrack(R.raw.action_sound_skip, new OnPlayCompliteListener() {
                                        @Override
                                        public void onComplite() {
                                            logicOfGame();
                                        }
                                    });
                                }
                            });
                        }
                    }).setDuration(150).start();

                }
                break;
            case R.id.ib_e_name:
                if (canTouch) {
                    view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate().scaleY(1f).scaleX(1f).start();
                                    SoundHelper.getInstance().playTrack(rightAnimal.getEnVoice(context), new OnPlayCompliteListener() {
                                        @Override
                                        public void onComplite() {

                                        }
                                    });
                                }
                            });
                        }
                    }).setDuration(150).start();

                }
                break;
            case R.id.ib_p_name:
                if (canTouch) {
                    view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate().scaleY(1f).scaleX(1f).start();
                                    SoundHelper.getInstance().playTrack(rightAnimal.getArmVoice(context), new OnPlayCompliteListener() {
                                        @Override
                                        public void onComplite() {

                                        }
                                    });
                                }
                            });
                        }
                    }).setDuration(150).start();

                }
                break;
            case R.id.ib_animal_sound:
                if (canTouch) {
                    view.animate().scaleY(0.8f).scaleX(0.8f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    view.animate().scaleY(1f).scaleX(1f).start();
                                    if (rightAnimal.getAnimalVoice(context)!=0){
                                        SoundHelper.getInstance().playTrack(rightAnimal.getAnimalVoice(context), new OnPlayCompliteListener() {
                                            @Override
                                            public void onComplite() {

                                            }
                                        });
                                    }

                                }
                            });
                        }
                    }).setDuration(150).start();

                }
                break;
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
