package com.sc.samples.filescan;

import android.os.Bundle;
import android.os.FileObserver;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sc.samples.BaseFragment;

import java.util.HashMap;
import java.util.concurrent.Executors;

/**
 * 文件扫描Demo
 */
public class FileScanFragment extends BaseFragment {

    private HashMap<String, FileObserver> mPathMap;
    private Handler mUiHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPathMap = new HashMap<>();
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                long time1 = System.currentTimeMillis();
                FileObserver ob = null;
                for (int i = 0; i <= 10000; i++) {
                    ob = new MyFileObserver("filePath://" + i);
                    ob.startWatching();
                    mPathMap.put("filePath://" + i, ob);
                }
                final long costTime = System.currentTimeMillis() - time1;

                mUiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "costTime = " + costTime, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private static final Runtime s_runtime = Runtime.getRuntime();

    private static long usedMemory() {
        return s_runtime.totalMemory() - s_runtime.freeMemory();
    }

    private static void runGC() throws Exception {
        long usedMem1 = usedMemory(), usedMem2 = Long.MAX_VALUE;
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++i) {
            s_runtime.runFinalization();
            s_runtime.gc();
            Thread.currentThread().yield();
            usedMem2 = usedMem1;
            usedMem1 = usedMemory();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private static class MyFileObserver extends FileObserver {

        private String mPath;

        public MyFileObserver(String path) {
            super(path);
            mPath = path;
        }

        @Override
        public void onEvent(int event, String path) {

        }
    }
}
