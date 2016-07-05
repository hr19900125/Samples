package com.sc.samples.codesnippet;

import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;

import com.sc.samples.BaseActivity;

import java.io.File;

/**
 *
 */
public class FileObserverActivity extends BaseActivity {

    private static final String TAG = "FileObserver";
    private FileObserver mFileObserver;

    @Override
    protected void click() {
        Log.e(TAG, "initFileObserver");
        initFileObserver();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        if(mFileObserver != null) {
            mFileObserver.stopWatching();
            mFileObserver = null;
        }
    }

    private void initFileObserver() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM";
        mFileObserver = new MyFileObserver(path, FileObserver.ALL_EVENTS);
        mFileObserver.startWatching();
        printlnToTextView("startWatching path = " + path);
    }

    private class MyFileObserver extends FileObserver {

        public MyFileObserver(String path, int mask) {
            super(path, mask);
        }

        public MyFileObserver(String path) {
            super(path);
        }

        @Override
        public void onEvent(int event, String path) {
            event &= FileObserver.ALL_EVENTS;
            printlnToTextView("onEvent = " + event);
        }
    }
}
