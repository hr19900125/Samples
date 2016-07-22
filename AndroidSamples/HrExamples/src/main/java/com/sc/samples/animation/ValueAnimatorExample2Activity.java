package com.sc.samples.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

/**
 *
 */
public class ValueAnimatorExample2Activity extends AppCompatActivity {

    private static final String TAG = "ValueAnimatorExample2Activity";

    private Button mButtonStart;
    private Button mButtonCancel;
    private TextView mTextView;
    private ValueAnimator mRepeatAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_example_2);

        mTextView = (TextView) findViewById(R.id.text);
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepeatAnimator = doRepeatAnim();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepeatAnimator.cancel();
            }
        });
    }

    private ValueAnimator doRepeatAnim() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 400);
        final int top = mTextView.getTop();
        final int bottom = mTextView.getBottom();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                mTextView.layout(mTextView.getLeft(), top + curValue, mTextView.getRight(), bottom + curValue);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "animation start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "animation end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "animation cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "animation repeat");
            }
        });
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setInterpolator(new MyInterploator());
        animator.setEvaluator(new MyEvaluator());
        animator.setDuration(1000);
        animator.start();
        return animator;
    }

    /**
     * 自定义Interploator
     */
    private static class MyInterploator implements TimeInterpolator {

        @Override
        public float getInterpolation(float input) {
            return 1 - input;
        }
    }

    /**
     * 自定义Evaluator
     */
    private static class MyEvaluator implements TypeEvaluator<Integer> {

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            return (int) (200 + startValue + (endValue - startValue) * fraction);
        }
    }
}
