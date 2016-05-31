package com.sc.samples.example;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 *
 */
public class TestLinearLayout extends LinearLayout {

    private static final String TAG = "TestLinearLayout";

    public TestLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "TestLinearLayout onInterceptTouchEvent-- action=" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "TestLinearLayout dispatchTouchEvent-- action=" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "TestLinearLayout onTouchEvent-- action=" + event.getAction());
        return super.onTouchEvent(event);
    }
}
