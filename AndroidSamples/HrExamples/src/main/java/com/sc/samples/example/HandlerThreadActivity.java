package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.sc.samples.R;

/**
 * HandlerThread Example
 */
public class HandlerThreadActivity extends Activity {

    private static final String TAG = "HandlerThreadActivity";

    private Handler mHandlerThread;
    private Handler mHandlerUi = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "在UI主线程中处理！id="+Thread.currentThread().getId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_1);

        initData();
    }

    private void initData() {
        Log.e(TAG, "Main Thread id=" + Thread.currentThread().getId());
        HandlerThread hthread = new HandlerThread("MyHandlerThread");
        hthread.start();

        mHandlerThread = new Handler(hthread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "在子线程中处理！id="+Thread.currentThread().getId());
                mHandlerUi.sendEmptyMessage(0);
            }
        };

        mHandlerThread.sendEmptyMessage(1);

    }
}
