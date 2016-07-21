package com.sc.samples.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.samples.R;

/**
 *
 */
public class ValueAnimatorExample1Activity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_example1);
        mTextView = (TextView) findViewById(R.id.text);
        mButton = (Button) findViewById(R.id.btn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation();
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ValueAnimatorExample1Activity.this, "textview clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doAnimation() {
        //数值从0 -> 400 -> 200 -> 300
        ValueAnimator animator = ValueAnimator.ofInt(0, 400, 200, 300);
        animator.setDuration(2000);
        final int left = mTextView.getLeft();
        final int top = mTextView.getTop();
        final int right = mTextView.getRight();
        final int bottom = mTextView.getBottom();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curValue = (int) animation.getAnimatedValue();
                mTextView.layout(left + curValue, top + curValue, right + curValue, bottom + curValue);
            }
        });
        animator.start();
    }
}
