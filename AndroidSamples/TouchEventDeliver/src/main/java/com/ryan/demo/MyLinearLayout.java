package com.ryan.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 */
public class MyLinearLayout extends LinearLayout{

    private static final String TAG = "MyLinearLayout";

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent()----------------" + this.toString());
        boolean isTrue = super.dispatchTouchEvent(ev);
        Log.e(TAG, "dispatchTouchEvent()  isTrue = " + isTrue + " --------------- ;"+ this.toString());
        return isTrue;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent()----------------" + this.toString());
        boolean isTrue = super.onTouchEvent(event);
        Log.e(TAG, "onTouchEvent()  isTrue = " + isTrue+ "-----------------  ;"+ this.toString());
        return isTrue;
    }
}
