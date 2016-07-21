package com.sc.samples.filescan;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 文件扫描服务
 */
public class ScanService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
