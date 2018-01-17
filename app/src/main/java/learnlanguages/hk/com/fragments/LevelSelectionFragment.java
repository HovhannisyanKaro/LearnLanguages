package learnlanguages.hk.com.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import learnlanguages.hk.com.activities.PlayActivity;
import learnlanguages.hk.com.controllers.DataController;
import learnlanguages.hk.com.controllers.ViewController;
import learnlanguages.hk.com.interfacies.Constants;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.SoundHelper;


public class LevelSelectionFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.tv_easy)
    TextView tvEasy;
    @BindView(R.id.tv_middle)
    TextView tvMiddle;
    @BindView(R.id.tv_hard)
    TextView tvHard;

    public LevelSelectionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_level_selection, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewController.getViewController().setLevelSelectionFragment(this);
        SoundHelper.getInstance().replayBackgorund();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.tv_easy, R.id.tv_middle, R.id.tv_hard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_easy:
                DataController.getInstance().setLevel(Constants.LEVELS.EASY);

                break;
            case R.id.tv_middle:
                DataController.getInstance().setLevel(Constants.LEVELS.MIDDLE);

                break;
            case R.id.tv_hard:
                DataController.getInstance().setLevel(Constants.LEVELS.HARD);
                break;
        }
        startActivity(new Intent(ViewController.getViewController().getContex(), PlayActivity.class));

    }
}
