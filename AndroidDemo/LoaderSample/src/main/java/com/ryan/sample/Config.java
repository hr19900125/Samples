package com.ryan.sample;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 *
 */
public class Config {

    public static final String CURRENT_DIRECTORY = "current_directory";

    public static void initImageLoader(Context context){
        //init image loader and content loader
        int maxImageWidthForCache = 240;
        int maxImageHeightForCache = 400;
        int maxCacheSize = 50 * 1024 * 1024; //50M
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(maxImageWidthForCache, maxImageHeightForCache)
                .diskCacheExtraOptions(maxImageWidthForCache, maxImageHeightForCache, null)
                .diskCacheSize(maxCacheSize)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
