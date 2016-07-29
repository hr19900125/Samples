package com.sc.samples.animation;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

/**
 * ObjectAnimator实现ArgbEvaluator ，颜色变化
 */
public class ObjectAnimatorExample3Activity extends AppCompatActivity {

    private Button mButtonStart;
    private Button mButtonCancel;
    private TextView mTextView;

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
                doArgbAnimator();
            }
        });

    }

    private void doArgbAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofInt(mTextView, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setDuration(2000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }

    public class ArgbEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24);
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                    (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                    (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                    (int) ((startB + (int) (fraction * (endB - startB))));
        }
    }
}
