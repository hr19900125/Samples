package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.sc.samples.R;

/**
 *
 * Android 触摸事件分发机制
 * http://blog.csdn.net/yanbober/article/details/45887547
 *
 */
public class TouchEventDispatch1Activity extends Activity implements View.OnTouchListener, View.OnClickListener{

    private static final String TAG = "TouchEventDispatch";

    private LinearLayout mLayout;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_1);

        mLayout = (LinearLayout) findViewById(R.id.layout);
        mButton = (Button) findViewById(R.id.btn);

        mLayout.setOnTouchListener(this);
        mButton.setOnTouchListener(this);

        mLayout.setOnClickListener(this);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "OnClickListener--onClick--"+v);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.e(TAG, "OnTouchListener--onTouch-- action="+event.getAction()+" --"+v);
        //假如onTouch 返回false， 事件会继续传递到onTouchEvent，所以onClick能够执行
        //假如onTouch 返回true， 事件不会继续传递了，onClick不会执行
        return false;
    }
}
