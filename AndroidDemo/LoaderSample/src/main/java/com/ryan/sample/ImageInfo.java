package com.ryan.sample;

/**
 *
 */
public class ImageInfo {

    String filePath;

    public static ImageInfo buildImageInfo(String path) {
        ImageInfo info = new ImageInfo();
        info.filePath = path;
        return info;
    }
}
