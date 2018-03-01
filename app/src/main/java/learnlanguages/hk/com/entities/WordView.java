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
import butterknife.OnClick;
import butterknife.Unbinder;
import learnlanguages.hk.com.learnlanguages.R;
import learnlanguages.hk.com.utils.ToastUtils;

/**
 * Created by Hovhannisyan.Karo on 06.01.2018.
 */

public class WordView extends RelativeLayout {

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.lw)
    RelativeLayout lw;
    private Context context;
    Unbinder unbinder;
    private OnLetterClickListener onLetterClickListener;

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
        lw.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            onLetterClickListener.onLetterClick(getThis());
        }
    };

    public void setOnLetterClickListener(OnLetterClickListener onLetterClickListener){
        this.onLetterClickListener = onLetterClickListener;
    }

    private WordView getThis(){
        return this;
    }

    public void setLetter(char letter) {
        tvWord.setText(String.valueOf(letter));
    }

    public char getLetter() {
        return tvWord.getText().toString().charAt(0);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unbinder.unbind();
    }

    public interface OnLetterClickListener{
        void onLetterClick(WordView wordView);
    }
}
