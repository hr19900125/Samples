package com.sc.samples.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sc.samples.R;
import com.sc.samples.widget.view.WaterRippleView;

/**
 *
 */

public class WaterRippleViewActivity extends AppCompatActivity implements View.OnClickListener {

    private WaterRippleView mWaterRippleView;
    private Button mStartBtn;
    private Button mStopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_ripple_view);
        mWaterRippleView = (WaterRippleView) findViewById(R.id.wrv_water);
        mStartBtn = (Button) findViewById(R.id.start_btn);
        mStopBtn = (Button) findViewById(R.id.stop_btn);
        mStartBtn.setOnClickListener(this);
        mStopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_btn:
                mWaterRippleView.start();
                break;
            case R.id.stop_btn:
                mWaterRippleView.stop();
                break;
        }
    }
}
