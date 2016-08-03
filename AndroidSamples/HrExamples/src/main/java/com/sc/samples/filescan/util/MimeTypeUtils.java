package com.sc.samples.filescan.util;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 文档相关的mimetype工具类
 */
public class MimeTypeUtils {

    private static final Map<String , String> extensionToMimeTypeMap = new HashMap<String, String>();
    private static final Map<String , String> mimeTypeToExtensionMap = new HashMap<String, String>();

    static {
        addMimeType("application/x-openvpn-profile", "ovpn");
        addMimeType("application/mtpk","mtpk");
    }

    private static void addMimeType(String mimeType, String extension){
        if (!extensionToMimeTypeMap.containsKey(mimeType)) {
            extensionToMimeTypeMap.put(mimeType, extension);
        }
        mimeTypeToExtensionMap.put(extension, mimeType);
    }

    /**
     * 获取文件的mimetype
     * @param filename
     * @return
     */
    public static String getMimeType(String filename){
        String ext = FileUtils.getExtension(filename);
        String mimeType = "application/mz-octet-stream";
        if(!TextUtils.isEmpty(ext)){
            mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext.toLowerCase());
            if(TextUtils.isEmpty(mimeType)){
                mimeType = mimeTypeToExtensionMap.get(ext.toLowerCase());
            }
        }
        if(TextUtils.isEmpty(mimeType)){
            mimeType = "application/mz-octet-stream";
        }
        return mimeType;
    }

}
