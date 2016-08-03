package com.sc.samples.filescan;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

import com.sc.samples.filescan.model.FileInfo;
import com.sc.samples.filescan.util.ApkUtils;
import com.sc.samples.filescan.util.FileUtils;
import com.sc.samples.filescan.util.MimeTypeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmResults;

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

    private static final String EXTRA_START_SCAN = "start_scan";

    private static final String ROOT_DIRECTORY = Environment.getExternalStorageDirectory().getPath();

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
        } else if (intent.hasExtra(EXTRA_START_SCAN)) {
            startScan();
        }
        return START_NOT_STICKY;
    }

    public static void startService(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ScanService.class);
        intent.putExtra(EXTRA_START_SCAN, true);
        context.startService(intent);
    }

    public static void stopService(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ScanService.class);
        intent.putExtra(EXTRA_STOP_SCAN, true);
        context.startService(intent);
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

        private static final long SLEEP_TIME = 60000;

        private volatile boolean finishScanThread = false;

        /**
         * 同步源文件和数据库信息用的，扫描过后mDeletedFiles中的数据就是需要在数据库删除的数据了
         */
        private Set<String> mDeletedFiles = new HashSet<>();

        /**
         * 扫描到的新的需要入库的文件
         */
        private Set<FileInfo> mNewFiles = new HashSet<>();

        private Realm mRealm;

        private int mId;

        Map<String, FileInfo> mFileInfoMap = new HashMap<>();

        public ScanThread() {
            super("Scan Thread");
        }

        public void finishScan() {
            finishScanThread = true;
        }

        /**
         * @param filePath
         */
        private void existedInRealm(String filePath) {
            mDeletedFiles.remove(filePath);
        }

        private void addNewFile(FileInfo fileInfo) {
            mNewFiles.add(fileInfo);
        }

        @Override
        public void run() {
            mRealm = Realm.getDefaultInstance();
            try {
                for (; ; ) {
                    long startTime = System.currentTimeMillis();
                    Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                    if (finishScanThread) break;
                    synchronized (ScanService.this) {
                        if (mScanThread != this) {
                            throw new IllegalStateException("multiple ScanThreads in ScanService");
                        }
                    }

                    RealmResults<FileInfo> fileInfos = mRealm.where(FileInfo.class).findAll();

                    Log.i(LOG_TAG, "FileInfo size : " + fileInfos.size());
                    mDeletedFiles = generateDeletedFileSet(fileInfos);
                    //执行全盘扫描
                    invokeScanningOverall();

                    Log.e(LOG_TAG, "ScanThread a scan cost time : " + (System.currentTimeMillis() - startTime));

                    //隔SLEEP_TIME时间执行一次
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                if (mRealm != null) {
                    mRealm.close();
                    mRealm = null;
                }
            }
        }

        private Set<String> generateDeletedFileSet(RealmResults<FileInfo> fileInfos) {
            Set<String> set = new HashSet<>();
            for (FileInfo info : fileInfos) {
                set.add(info.getFilePath());
            }
            return set;
        }

        /**
         * 先使用空间比较多的方式，全盘扫描
         */
        private void invokeScanningOverall() {
            List<FileInfo> mScanning = new ArrayList<>();
            String curDir = ROOT_DIRECTORY;
            File rootFile = new File(curDir);
            mScanning.add(addOrUpdate(buildFileInfo(rootFile, -1)));
            int parentIndex = 0;
            FileInfo parent = null;
            File parentFile = null;
            while (parentIndex < mScanning.size()) {
                parent = mScanning.get(parentIndex);
                if (parent.getFileType() != FileType.TYPE_DIRECTORY) {
                    parentIndex++;
                    continue;
                }
                parentFile = new File(parent.getFilePath());
                File[] childFiles = parentFile.listFiles();
                if (childFiles != null) {
                    File child;
                    for (int childIndex = 0, length = childFiles.length; childIndex < length; childIndex++) {
                        child = childFiles[childIndex];
                        if(child.isHidden() || !child.exists()) continue;
                        mScanning.add(addOrUpdate(buildFileInfo(child, parent.getId())));
                    }
                }
                parentIndex++;
            }
            mScanning.clear();
        }

        private FileInfo buildFileInfo(File file, long parentId) {
            long time = System.currentTimeMillis();
            FileInfo fileInfo = new FileInfo();
            String filePath = file.getPath();
            fileInfo.setParentId(parentId);
            fileInfo.setFilePath(filePath);
            fileInfo.setFileName(FileUtils.getFileName(filePath));
            String mimeType = MimeTypeUtils.getMimeType(filePath);
            int fileType = FileType.TYPE_NORMAL;
            if (file.isDirectory()) {
                fileType = FileType.TYPE_DIRECTORY;
            } else if (file.isFile()) {
                String ext = FileUtils.getExtension(filePath);
                fileType = SuffixMap.mapType(ext);
                if (FileType.TYPE_APK == fileType) {
//                    fileInfo.setApkLabel(ApkUtils.apkLabel(ScanService.this, filePath));
                }
            }
            fileInfo.setFileType(fileType);
            fileInfo.setMimeType(mimeType);
            fileInfo.setFileSize(file.length());
            fileInfo.setModifiedDate(file.lastModified());
            Log.e(LOG_TAG, "build one FileInfo cost time : " + (System.currentTimeMillis() - time));
            return fileInfo;
        }

        private FileInfo addOrUpdate(final FileInfo fileInfo) {
            if(!mDeletedFiles.contains(fileInfo.getFilePath())) {
                //新增的数据
//                mRealm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        fileInfo.setId(mId++);
//                        realm.copyToRealm(fileInfo);
//                    }
//                });

            } else {
                //删除的数据
                mDeletedFiles.remove(fileInfo.getFilePath());
            }
//            mFileInfoMap.put(fileInfo.getFilePath(), fileInfo);
            return fileInfo;
        }
    }
}
