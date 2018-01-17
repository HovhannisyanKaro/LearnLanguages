package learnlanguages.hk.com.entities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import learnlanguages.hk.com.learnlanguages.R;

/**
 * Created by Hovhannisyan.Karo on 06.01.2018.
 */

public class WordView extends RelativeLayout {

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.lw)
    FrameLayout lw;
    private LayoutInflater inflater;
    private Context context;
    Unbinder unbinder;

    public WordView(Context context) {
        super(context);
        init(context);
    }

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_word, this);
        unbinder = ButterKnife.bind(this, view);

        lw.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void setLetter(char letter) {
        tvWord.setText(String.valueOf(letter));
    }

    public char getLetter() {
        return tvWord.getText().toString().toCharArray()[0];
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }
}
