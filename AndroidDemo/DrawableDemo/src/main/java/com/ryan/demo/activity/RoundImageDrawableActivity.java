package com.ryan.demo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.ryan.demo.R;
import com.ryan.demo.drawable.RoundImageDrawable;

/**
 *
 */
public class RoundImageDrawableActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_drawable_view);
        ImageView image = (ImageView) findViewById(R.id.image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image1);
        image.setImageDrawable(new RoundImageDrawable(bitmap));
    }
}
