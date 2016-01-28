package com.ryan.toolbox.camera.textureview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

import com.ryan.toolbox.camera.surfaceview.CameraInterface;

/**
 *
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class CameraTextureView extends TextureView implements TextureView.SurfaceTextureListener{

    private static final String TAG = "CameraTextureView";

    private SurfaceTexture mSurface;

    public CameraTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Log.i(TAG, "onSurfaceTextureAvailable ...");
        mSurface = surface;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        Log.i(TAG, "onSurfaceTextureSizeChanged ...");
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        Log.i(TAG, "onSurfaceTextureDestroyed ...");
        CameraInterface.getInstance().doStopCamera();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        Log.i(TAG, "onSurfaceTextureUpdated ...");
    }

    public SurfaceTexture getSurfaceTexture() {
        return mSurface;
    }
}
