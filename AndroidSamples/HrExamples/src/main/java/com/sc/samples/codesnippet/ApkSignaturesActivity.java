package com.sc.samples.codesnippet;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.sc.samples.BaseActivity;

import java.security.MessageDigest;

/**
 * 获取应用签名
 */
public class ApkSignaturesActivity extends BaseActivity {

    @Override
    protected void click() {
        try {
            printlnToTextView(getAppSignPublicKey(this, "com.meizu.filemanager"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final static char[] hexDigits = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = hexDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = hexDigits[0x0F & data[i]];
        }
        return new String(out);
    }

    public static String md5sum(byte[] original) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(original);
            String str = encodeHex(md5.digest());
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAppSignPublicKey(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            Signature[] signs = info.signatures;
            if (signs != null && signs.length == 1) {
                String keyValue = md5sum(signs[0].toByteArray());
                return keyValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
