package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sc.samples.R;

/**
 * Android 触摸事件分发机制 ViewGroup
 * http://blog.csdn.net/yanbober/article/details/45912661
 */
public class TouchEventDispatch2Activity extends Activity implements View.OnTouchListener, View.OnClickListener {

    private static final String TAG = "TouchEventDispatch2";

    private TestLinearLayout mLayout;
    private TestButton mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_2);

        mLayout = (TestLinearLayout) findViewById(R.id.layout);
        mButton = (TestButton) findViewById(R.id.btn);

        mLayout.setOnTouchListener(this);
        mButton.setOnTouchListener(this);

        mLayout.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "OnClickListener--onClick--" + v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "OnTouchListener--onTouch-- action=" + event.getAction() + " --" + v);
        return false;
    }
}
