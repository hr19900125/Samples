package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.ryan.toolbox.R;
import com.ryan.toolbox.utils.PinchZoom;

/**
 *
 */
public class PinchZoomImageViewAct extends Activity{

    Context mContext;
    ImageView ivImagePich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinch_zoom_image_view);
        mContext = PinchZoomImageViewAct.this;

        init();

    }

    private void init() {
        ivImagePich = (ImageView) findViewById(R.id.ivImagePich);
        ivImagePich.setOnTouchListener(new PinchZoom());
    }
}
