package com.sc.samples.appframework;

import android.os.Bundle;

import com.sc.samples.BaseActivity;
import com.sc.samples.appframework.logback.FileLoggingTree;

import timber.log.Timber;

/**
 *
 */
public class LogFrameActivity extends BaseActivity {

    private boolean DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new FileLoggingTree());
        }
    }

    @Override
    protected void click() {
        Timber.e("hello one");
        Timber.e("hello two");
    }
}
