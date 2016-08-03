package com.sc.samples.filescan;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class SuffixMap {

    private static Map<String, Integer> map = new HashMap<>();

    static {

        /**
         * 音乐
         */
        map.put("mp3", FileType.TYPE_AUDIO);
        map.put("wma", FileType.TYPE_AUDIO);
        map.put("wav", FileType.TYPE_AUDIO);
        map.put("ape", FileType.TYPE_AUDIO);
        map.put("flac", FileType.TYPE_AUDIO);
        map.put("ogg", FileType.TYPE_AUDIO);
        map.put("aac", FileType.TYPE_AUDIO);

        /**
         * 视频
         */
        map.put("avi", FileType.TYPE_VIDEO);
        map.put("rm", FileType.TYPE_VIDEO);
        map.put("rmvb", FileType.TYPE_VIDEO);
        map.put("mov", FileType.TYPE_VIDEO);
        map.put("wmv", FileType.TYPE_VIDEO);
        map.put("mp4", FileType.TYPE_VIDEO);
        map.put("mpg", FileType.TYPE_VIDEO);
        map.put("mpeg", FileType.TYPE_VIDEO);
        map.put("dts", FileType.TYPE_VIDEO);
        map.put("ts", FileType.TYPE_VIDEO);
        map.put("vob", FileType.TYPE_VIDEO);
        map.put("flv", FileType.TYPE_VIDEO);
        map.put("mkv", FileType.TYPE_VIDEO);
        map.put("3gp", FileType.TYPE_VIDEO);
        map.put("3gpp", FileType.TYPE_VIDEO);
        map.put("3g2", FileType.TYPE_VIDEO);

        /**
         * 图片
         */
        map.put("bmp", FileType.TYPE_IMAGE);
        map.put("wbmp", FileType.TYPE_IMAGE);
        map.put("gif", FileType.TYPE_IMAGE);
        map.put("jpeg", FileType.TYPE_IMAGE);
        map.put("psd", FileType.TYPE_IMAGE);
        map.put("png", FileType.TYPE_IMAGE);
        map.put("tiff", FileType.TYPE_IMAGE);
        map.put("tif", FileType.TYPE_IMAGE);
        map.put("jpg", FileType.TYPE_IMAGE);
        map.put("ai", FileType.TYPE_IMAGE);

        /**
         * 压缩包
         */
        map.put("rar", FileType.TYPE_ZIP);
        map.put("zip", FileType.TYPE_ZIP);
        map.put("bin", FileType.TYPE_ZIP);
        map.put("iso", FileType.TYPE_ZIP);

        /**
         * 文档
         */
        map.put("ppt", FileType.TYPE_DOC);
        map.put("pptx", FileType.TYPE_DOC);
        map.put("wps", FileType.TYPE_DOC);
        map.put("pot", FileType.TYPE_DOC);
        map.put("potx", FileType.TYPE_DOC);
        map.put("pps", FileType.TYPE_DOC);
        map.put("ppsx", FileType.TYPE_DOC);
        map.put("xlsx", FileType.TYPE_DOC);
        map.put("xls", FileType.TYPE_DOC);
        map.put("xlt", FileType.TYPE_DOC);
        map.put("xltx", FileType.TYPE_DOC);
        map.put("doc", FileType.TYPE_DOC);
        map.put("dot", FileType.TYPE_DOC);
        map.put("docx", FileType.TYPE_DOC);
        map.put("dotx", FileType.TYPE_DOC);
        map.put("txt", FileType.TYPE_DOC);
        map.put("pdf", FileType.TYPE_DOC);
        map.put("epub", FileType.TYPE_DOC);
        map.put("fb2", FileType.TYPE_DOC);
        map.put("rtf", FileType.TYPE_DOC);
        map.put("mobi", FileType.TYPE_DOC);
        map.put("prc", FileType.TYPE_DOC);

        /**
         * apk
         */
        map.put("apk", FileType.TYPE_APK);

    }

    public static int mapType(String ext) {
        Integer t = map.get(ext);
        if (t != null) {
            return t;
        } else {
            return FileType.TYPE_NORMAL;
        }

    }

}
