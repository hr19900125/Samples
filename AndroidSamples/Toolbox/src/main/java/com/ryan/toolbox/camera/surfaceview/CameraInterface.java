package com.ryan.toolbox.camera.surfaceview;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.IOException;
import java.util.List;

/**
 * 相机操作封装
 */
public class CameraInterface {

    private static final String TAG = "CameraInterface";

    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;
    private float mPreviewRate = -1f;
    private static CameraInterface mCameraInterface;

    private CameraInterface() {

    }

    public interface CamOpenOverCallback {
        public void cameraHasOpened();
    }

    public static synchronized CameraInterface getInstance() {
        if(mCameraInterface == null) {
            mCameraInterface = new CameraInterface();
        }
        return mCameraInterface;
    }

    /**
     * 打开Camera
     * @param callback
     */
    public void doOpenCamera(CamOpenOverCallback callback) {
        Log.i(TAG, "Camera open ...");
        mCamera = Camera.open();
        Log.i(TAG, "Camera open over ...");
        callback.cameraHasOpened();
    }

    /**
     * 开启预览
     * @param holder
     * @param previewRate
     */
    public void doStartPreview(SurfaceHolder holder, float previewRate) {
        Log.i(TAG, "doStartPreview ...");
        if(isPreviewing) {
            mCamera.stopPreview();
            return;
        }

        if(mCamera != null) {
            try {
                mCamera.setPreviewDisplay(holder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }
    }

    /**
     * 给TextureView预览调用的
     * @param surface
     * @param previewRate
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void doStartPreview(SurfaceTexture surface, float previewRate) {
        Log.i(TAG, "TextureView doStartPreview ...");
        if(isPreviewing) {
            mCamera.stopPreview();
            return;
        }

        if(mCamera != null) {
            try {
                mCamera.setPreviewTexture(surface);
            } catch (IOException e) {
                e.printStackTrace();
            }
            initCamera(previewRate);
        }
    }

    private void initCamera(float previewRate) {
        Log.i(TAG, "initCamera ...");
        if(mCamera != null) {
            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);
            CameraParaUtil.getInstance().printSupportPictureSize(mParams);
            CameraParaUtil.getInstance().printSupportPreviewSize(mParams);
            //设置PreviewSize和PictureSize
            Camera.Size pictureSize = CameraParaUtil.getInstance().getPropPictureSize(mParams.getSupportedPictureSizes(),previewRate, 800);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Camera.Size previewSize = CameraParaUtil.getInstance().getPropPreviewSize(mParams.getSupportedPreviewSizes(),previewRate, 800);
            mParams.setPreviewSize(previewSize.width, previewSize.height);
            mCamera.setDisplayOrientation(90);

            CameraParaUtil.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if(focusModes.contains("continuous-video")){
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);
            mCamera.startPreview(); //开启预览

            isPreviewing = true;
            mPreviewRate = previewRate;

            mParams = mCamera.getParameters(); //重新get一次
            Log.i(TAG, "最终设置:PreviewSize--With = " + mParams.getPreviewSize().width
                    + "Height = " + mParams.getPreviewSize().height);
            Log.i(TAG, "最终设置:PictureSize--With = " + mParams.getPictureSize().width
                    + "Height = " + mParams.getPictureSize().height);
        }
    }

    /**
     * 停止预览，示范Camera
     */
    public void doStopCamera() {
        if(mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mPreviewRate = -1f;
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 拍照
     */
    public void doTakePicture() {
        if(isPreviewing && (mCamera != null)) {
            mCamera.takePicture(mShutterCallback, null, mJpegPictureCallback);
        }
    }


    /**
     * 为了实现拍照的快门声音及拍照保存照片需要下面三个回调变量
     */
    Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
        @Override
        public void onShutter() {
            //快门按下的回调，在这里我们可以设置类似播放“咔嚓”声之类的操作。默认的就是咔嚓。
            Log.i(TAG, "mShutterCallback:onShutter ...");
        }
    };

    Camera.PictureCallback mRawCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //拍摄的未压缩原数据的回调,可以为null
            Log.i(TAG, "mRawCallback:onPictureTaken ...");
        }
    };

    Camera.PictureCallback mJpegPictureCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //对jpeg图像数据的回调,最重要的一个回调
            Log.i(TAG ,"mJpegPictureCallback:onPictureTaken ...");
            Bitmap b = null;
            if(data != null) {
                b = BitmapFactory.decodeByteArray(data, 0 ,data.length); //data是字节数组，将其解析成位图
                mCamera.stopPreview();
                isPreviewing = false;
            }
            if(b != null) {
                //设置FOCUS_MODE_CONTINUOUS_VIDEO)之后，myParam.set("rotation", 90)失效。
                //图片竟然不能旋转了，故这里要旋转下
                Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                FileUtil.saveBitmap(rotaBitmap);
            }
            //再次进入预览
            mCamera.startPreview();
            isPreviewing = true;
        }
    };

}
