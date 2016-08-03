package com.sc.samples.filescan.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 *
 */
public class ApkUtils {

    public static String apkLabel(Context context, String apkPath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            /* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
            String appName = pm.getApplicationLabel(appInfo).toString();// 得到应用名
            return appName;
        }
        return null;
    }

}
