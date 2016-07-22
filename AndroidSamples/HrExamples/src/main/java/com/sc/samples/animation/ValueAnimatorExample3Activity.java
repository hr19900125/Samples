package com.sc.samples.animation;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

/**
 * ValueAnimator ofObject 使用
 */
public class ValueAnimatorExample3Activity extends AppCompatActivity{

    private Button mButtonStart;
    private Button mButtonCancel;
    private TextView mTextView;
    private MyPointView mPointView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_example_2);

        mTextView = (TextView) findViewById(R.id.text);
        mButtonStart = (Button) findViewById(R.id.btn_start);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);
        mPointView = (MyPointView) findViewById(R.id.point_view);
        mPointView.setVisibility(View.VISIBLE);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPointView.doPointAnim();
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
