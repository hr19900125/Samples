package com.sc.samples.filescan;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

/**
 * 文件扫描服务
 * <p/>
 * 腾讯文件监听：
 * 1.只有在进入过分类里面进入手机存储后目录才会被监听
 * 2.断网并清除数据的情况下只有存储盘根目录会监听，但是假如进入过分类里面监听生效，所以说监听和网络无关
 * 3.假如一进入腾讯文件管理器不进入分类，使用第三方应用删除图片文件，分类文件是监听不到数据的
 * 4.退出应用再进入会重新计算分类
 * 5.进入会开始监听的分类（图片、音乐、视频、安装包、压缩包、文档、密盒）,收藏，应用不会
 * 6.发现没有被监听到的数据并不是没有显示，而是没有入库，排除入库了但是没有显示的猜测
 * 7.腾讯文件管理器的file数据库没有包括隐藏文件
 * 8.在存在监听的情况下，使用adb push 文件到不包括类型的文件，发现比较长时间之后才监听到。而push文件到存在类型文件的目录，比较快就能出来。
 * 9.在腾讯文件管理器的包下没有发现有关扫描的so，猜测可能他们是java实现的。
 * 10.sd卡也会扫描入库
 * 猜测腾讯文件管理使用了定时加监听的方式来实现全盘扫描功能
 * <p/>
 * 计划：
 * 1.使用定时或者死循环保护线程的方式来实现全盘扫描
 * 2.增加监听的方式还辅助全盘扫描机制
 * <p/>
 * 迅雷文件管理器
 * 1.迅雷文件管理器采用是主动出发扫描的方式
 * <p/>
 * 得出结论：
 * <p/>
 * <p/>
 * 设计：
 * 考虑当用户存在sd卡的时候需要扫描sd卡的文件入库
 */
public class ScanService extends Service {

    private static final String LOG_TAG = "ScanService";

    private static final String EXTRA_STOP_SCAN = "stop_scan";

    /**
     * 全盘扫描线程
     */
    private ScanThread mScanThread;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(LOG_TAG, "ScanService onCreate");
        startScan();
    }

    private void startScan() {
        synchronized (this) {
            if (mScanThread == null) {
                mScanThread = new ScanThread();
                mScanThread.start();
            }
        }
    }

    private void finishScan() {
        synchronized (this) {
            if (mScanThread != null) {
                mScanThread.finishScan();
                mScanThread = null;
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOG_TAG, "ScanService onStartCommand");
        if (intent.hasExtra(EXTRA_STOP_SCAN)) {
            finishScan();
            stopSelf();
        } else {
            startScan();
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Can not support bind ScanService");
    }

    @Override
    public void onDestroy() {
        Log.i(LOG_TAG, "ScanService onDestroy");
        super.onDestroy();
    }

    private class ScanThread extends Thread {

        private static final long SLEEP_TIME = 10000;

        private volatile boolean finishScanThread = false;

        public ScanThread() {
            super("Scan Thread");
        }

        public void finishScan() {
            finishScanThread = true;
        }

        @Override
        public void run() {
            for (; ; ) {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                synchronized (ScanService.this) {
                    if (mScanThread != this) {
                        throw new IllegalStateException("multiple ScanThreads in ScanService");
                    }
                }
                if (finishScanThread) break;



                //隔SLEEP_TIME时间执行一次
                try {
                    Thread.sleep(SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
