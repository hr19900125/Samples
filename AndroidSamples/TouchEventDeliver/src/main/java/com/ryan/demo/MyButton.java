package com.ryan.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 *
 */
public class MyButton extends Button {

    private static final String TAG = "MyButton";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent()----------------");
        boolean isTrue = super.dispatchTouchEvent(event);
        Log.e(TAG, "dispatchTouchEvent  isTrue = " + isTrue);
        return isTrue;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent()----------------");
        boolean isTrue = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent  isTrue = " + isTrue);
        return isTrue;
    }
}
