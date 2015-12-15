package com.ryan.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 *
 */
public class MyButtonWithOutEvent extends Button{

    private static final String TAG = "MyButtonWithOutEvent";

    public MyButtonWithOutEvent(Context context) {
        super(context);
    }

    public MyButtonWithOutEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent()----------------" + this.toString());
//      boolean isTrue = super.dispatchTouchEvent(event);
//      Log.e(TAG, "isTrue = " + isTrue);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent()----------------");
        boolean isTrue = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent  isTrue = " + isTrue);
        return isTrue;
    }
}
