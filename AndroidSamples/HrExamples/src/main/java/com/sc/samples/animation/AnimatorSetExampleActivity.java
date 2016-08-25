package com.sc.samples.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

/**
 * http://blog.csdn.net/harvic880925/article/details/50759059
 */
public class AnimatorSetExampleActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AnimatorSetExample";

    private Button mButton,mCancelButton;
    private TextView mTextView1, mTextView2;

    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animatorset_example);

        mButton = (Button) findViewById(R.id.btn);
        mCancelButton = (Button) findViewById(R.id.cancel_btn);
        mTextView1 = (TextView) findViewById(R.id.tv_1);
        mTextView2 = (TextView) findViewById(R.id.tv_2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doPlaySequentiallyAnimator();
//                doPlayTogetherAnimator();
                doAnimatorWithAnimatorBuilder();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAnimatorSet();
            }
        });
    }

    /**
     * playSequentially 逐帧动画，动画一个个执行
     */
    private void doPlaySequentiallyAnimator() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(mTextView1, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mTextView1, "translationY", 0, 300, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mTextView2, "translationY", 0, 400, 0);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playSequentially(tv1BgAnimator, tv1TranslateY, tv2TranslateY);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

    /**
     * playTogether 同时执行动画
     */
    private void doPlayTogetherAnimator() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(mTextView1, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mTextView1, "translationY", 0, 300, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mTextView2, "translationY", 0, 400, 0);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(tv1BgAnimator, tv1TranslateY, tv2TranslateY);
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
    }

    /**
     * 使用Animator.Builder 自由设置动画顺序
     */
    private void doAnimatorWithAnimatorBuilder() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(mTextView1, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mTextView1, "translationY", 0, 300, 0);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mTextView2, "translationY", 0, 400, 0);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(2000);
        AnimatorSet.Builder builder = mAnimatorSet.play(tv1BgAnimator);
        builder.with(tv1TranslateY);
        builder.before(tv2TranslateY);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(LOG_TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(LOG_TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(LOG_TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(LOG_TAG, "onAnimationRepeat");
            }
        });
        mAnimatorSet.start();
    }

    private void cancelAnimatorSet() {
        if(mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
    }
}
