package com.ryan.sample;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

/**
 *
 */
public class MimeTypeUtils {

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
        }
        if(TextUtils.isEmpty(mimeType)){
            mimeType = "application/mz-octet-stream";
        }
        return mimeType;
    }

}
