package com.ryan.toolbox.camera.textureview;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.ryan.toolbox.R;
import com.ryan.toolbox.camera.surfaceview.CameraInterface;
import com.ryan.toolbox.camera.surfaceview.DisplayUtil;

/**
 *
 */
public class CameraActivity extends Activity implements CameraInterface.CamOpenOverCallback{

    private static final String TAG = "CameraActivity";
    CameraTextureView textureView = null;
    ImageButton shutterBtn;
    float previewRate = -1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread openThread = new Thread() {
            @Override
            public void run() {
                CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
            }
        };
        openThread.start();
        setContentView(R.layout.camera_texture_activity);
        initUI();
        initViewParams();

        shutterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraInterface.getInstance().doTakePicture();
            }
        });
    }

    private void initUI() {
        textureView = (CameraTextureView) findViewById(R.id.camera_surfaceview);
        shutterBtn = (ImageButton) findViewById(R.id.btn_shutter);
    }

    private void initViewParams() {
        ViewGroup.LayoutParams params = textureView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x - 100;
        params.height = p.y - 100;
        previewRate = DisplayUtil.getScreenRate(this); //默认全屏的比例预览
        textureView.setLayoutParams(params);

        //手动拍照Button
        ViewGroup.LayoutParams p2 = shutterBtn.getLayoutParams();
        p2.width = DisplayUtil.dip2px(this, 80);
        p2.height = DisplayUtil.dip2px(this, 80);
        shutterBtn.setLayoutParams(p2);
    }

    @Override
    public void cameraHasOpened() {
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        CameraInterface.getInstance().doStartPreview(surfaceTexture, previewRate);
    }
}
