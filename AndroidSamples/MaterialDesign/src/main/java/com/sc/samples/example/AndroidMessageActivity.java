package com.sc.samples.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.sc.samples.R;

/**
 * Android异步消息处理机制详解及源码分析
 * http://blog.csdn.net/yanbober/article/details/45936145
 */
public class AndroidMessageActivity extends Activity {

    private static final String TAG = "AndroidMessage";

    private int mCount = 0;
    private Handler mHandlerThread = null;
    private Handler mHandlerUi = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, ">>>>>>>>>>>>>UI# mHandler--handleMessage--msg.what="+msg.what);
            mHandlerThread.sendEmptyMessage(1);
            mCount++;
            if (mCount > 3) {
                mHandlerThread.getLooper().quit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchevent_dispatch_1);

        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mHandlerUi.removeCallbacksAndMessages(null);
    }

    private void initData() {
        Log.e(TAG, ">>>>>>>>>>>>>UI#begin thread");
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                //在这里可以做些初始化的工作，比如初始化线程的Handler
                mHandlerThread = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Log.e(TAG, ">>>>>>>>>>>>>Child# mHandlerThread--handleMessage--msg.what=" + msg.what);
                        //接收发送到子线程的消息，然后向UI线程中的Handler发送msg 0。
                        mHandlerUi.sendEmptyMessage(0);
                    }
                };

                Log.e(TAG, ">>>>>>>>>>>>>Child#begin start and send msg");
                mHandlerUi.sendEmptyMessage(0);
                Looper.loop();
                //Looper.loop源码里面是个for(;;)循环，所以Looper.loop()之后的代码是不会执行的
            }
        }.start();
    }
}
