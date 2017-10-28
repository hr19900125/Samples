package com.sc.samples.codesnippet;

import android.os.Handler;
import android.util.Log;

import com.sc.samples.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */

public class OnceThreadPoolActivity extends BaseActivity {

    private static final String TAG = "OnceThreadPool";
    private WeakReference<ExecutorService> mRef;

    private Handler mUiHandler = new Handler();

    @Override
    protected void click() {
        runOnce();
    }

    private void runOnce() {
        final ExecutorService es = Executors.newSingleThreadExecutor();
        mRef = new WeakReference<ExecutorService>(es);
        Runnable runnable = new MyRunnable(es);
        es.execute(runnable);
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.gc();
            }
        }, 10000);
        mUiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.gc();
                if (mRef.get() != null) {
                    Log.e(TAG, "not gc " + mRef.get().isShutdown());
                    printlnToTextView("not gc " + mRef.get().isShutdown());
                } else {
                    Log.e(TAG, "has gc");
                    printlnToTextView("has gc");
                }
            }
        }, 30000);
    }

    private class MyRunnable implements Runnable {

        ExecutorService executor;

        public MyRunnable(ExecutorService es) {
            executor = es;
        }

        @Override
        public void run() {
            Log.e(TAG, "begin run");
            try {
                Thread.currentThread().sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e(TAG, "end run");
            executor.shutdown();
            executor = null;
        }
    }

}
