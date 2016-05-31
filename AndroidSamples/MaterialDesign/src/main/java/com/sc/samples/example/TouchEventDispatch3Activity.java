package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sc.samples.R;

/**
 * Android 触摸事件分发机制 Activity
 * http://blog.csdn.net/yanbober/article/details/45932123
 */
public class TouchEventDispatch3Activity extends Activity implements View.OnTouchListener, View.OnClickListener {

    private static final String TAG = "Dispatch3";

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
        Log.e(TAG, "onClick----v=" + v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "onTouch--action=" + event.getAction() + "--v=" + v);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "TouchEventDispatch3Activity--dispatchTouchEvent--action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onUserInteraction() {
        Log.e(TAG, "TouchEventDispatch3Activity--onUserInteraction");
        super.onUserInteraction();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "TouchEventDispatch3Activity--onTouchEvent--action=" + event.getAction());
        return super.onTouchEvent(event);
    }
}
