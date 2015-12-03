package com.ryan.sample;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ImageInfoLoader extends FMAsyncTaskLoader<ImageInfoResult>{

    private String mDirectory;
    private ImageInfoResult mResult;

    public ImageInfoLoader(Context context, String directory) {
        super(context);
        mDirectory = directory;
    }

    @Override
    public ImageInfoResult loadInBackground() {
        List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
        File file = new File(mDirectory);
        if(!file.exists()) {
            file.mkdirs();
        }
        File[] files = file.listFiles();
        if(files != null) {
            for(File f : files) {
                String path = f.getAbsolutePath();
                String mimeType = MimeTypeUtils.getMimeType(FileUtils.getFileName(path));
                if(mimeType != null && (mimeType.startsWith("image/"))) {
                    imageInfos.add(ImageInfo.buildImageInfo(path));
                }
            }
        }
        ImageInfoResult result = new ImageInfoResult();
        result.setImageInfos(imageInfos);
        return result;
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }

    @Override
    public void deliverResult(ImageInfoResult result) {
        if (isReset()) {
            return;
        }
        mResult = result;

        if (isStarted()) {
            super.deliverResult(result);
        }

    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
        }
        if (takeContentChanged() || mResult == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }


    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader is stopped
        onStopLoading();

//        IoUtils.closeQuietly(mResult);
        mResult = null;

    }
}
