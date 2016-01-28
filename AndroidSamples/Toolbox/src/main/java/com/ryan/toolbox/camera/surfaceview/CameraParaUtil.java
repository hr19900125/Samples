package com.ryan.toolbox.camera.surfaceview;

import android.hardware.Camera;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class CameraParaUtil {

    private static final String TAG = "CameraParaUtil";
    private CameraSizeComparator sizeComparator = new CameraSizeComparator();
    private static CameraParaUtil mInstance = null;

    private CameraParaUtil() {

    }

    public static CameraParaUtil getInstance() {
        if (mInstance == null) {
            mInstance = new CameraParaUtil();
        }
        return mInstance;
    }

    /**
     * 获取最合适的preview的Size
     * @param list
     * @param th
     * @param minWidth
     * @return
     */
    public Camera.Size getPropPreviewSize(List<Camera.Size> list, float th, int minWidth) {
        Collections.sort(list, sizeComparator);

        int i = 0;
        for(Camera.Size s : list) {
            if((s.width >= minWidth) && equalRate(s, th)) {
                Log.i(TAG, "PictureSize : w = " + s.width + "h = " + s.height);
                break;
            }
            i ++;
        }

        if(i == list.size()) {
            i = 0; //如果没找到， 就选最小的size
        }
        return list.get(i);
    }

    public Camera.Size getPropPictureSize(List<Camera.Size> list, float th, int minWidth){
        Collections.sort(list, sizeComparator);

        int i = 0;
        for(Camera.Size s:list){
            if((s.width >= minWidth) && equalRate(s, th)){
                Log.i(TAG, "PictureSize : w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }
        if(i == list.size()){
            i = 0;//如果没找到，就选最小的size
        }
        return list.get(i);
    }

    public boolean equalRate(Camera.Size s, float rate) {
        float r = (float) (s.width) / (float) (s.height);
        if (Math.abs(r - rate) <= 0.03) {
            return true;
        } else {
            return false;
        }
    }

    public class CameraSizeComparator implements Comparator<Camera.Size> {
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            // TODO Auto-generated method stub
            if (lhs.width == rhs.width) {
                return 0;
            } else if (lhs.width > rhs.width) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * Print the supported preview sizes.
     * @param params
     */
    public void printSupportPreviewSize(Camera.Parameters params) {
        List<Camera.Size> previewSizes = params.getSupportedPreviewSizes();
        for(int i = 0; i < previewSizes.size(); i ++) {
            Camera.Size size = previewSizes.get(i);
            Log.i(TAG, "previewSizes:width = "+size.width+" height = "+size.height);
        }
    }

    /**
     * Print the supported picture sizes
     * @param params
     */
    public void printSupportPictureSize(Camera.Parameters params) {
        List<Camera.Size> pictureSizes = params.getSupportedPictureSizes();
        for(int i = 0; i < pictureSizes.size();i ++) {
            Camera.Size size = pictureSizes.get(i);
            Log.i(TAG, "pictureSizes:width = "+ size.width + " height = " + size.height);
        }
    }

    /**
     * Print the supported focus modes.
     * @param params
     */
    public void printSupportFocusMode(Camera.Parameters params) {
        List<String> focusModes = params.getSupportedFocusModes();
        for(String mode : focusModes) {
            Log.i(TAG, "focusModes--" + mode);
        }
    }

}
