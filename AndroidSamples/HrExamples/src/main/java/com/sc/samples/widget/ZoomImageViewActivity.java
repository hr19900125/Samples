package com.sc.samples.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sc.samples.R;

/**
 *
 */

public class ZoomImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView mImageView;
    Button mZoomBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image_view);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mImageView.setScaleType(ImageView.ScaleType.MATRIX);
        mZoomBtn = (Button) findViewById(R.id.zoom_btn);
        mZoomBtn.setOnClickListener(this);
//        Matrix matrix = mImageView.getImageMatrix();
//        matrix.postTranslate(-120, 0);
//        mImageView.setImageMatrix(matrix);
    }

    @Override
    public void onClick(View v) {
        mImageView.setImageBitmap(generateBitmap("ftp://172.17.112.150:2121"));
    }

    private TextPaint getTextPaint() {
        TextPaint paint = new TextPaint();
        paint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        paint.setColor(getResources().getColor(R.color.black));
        paint.setAlpha(127);
        paint.setAntiAlias(true);
        paint.setTextSkewX(0.35f);
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.ip_text_size));
        paint.setStyle(Paint.Style.FILL);
        return paint;
    }

    private Bitmap generateBitmap(String ipText) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ftp_icon_view2);
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), config);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.save();
        canvas.rotate(21f);
        canvas.drawText(ipText, getResources().getDimensionPixelSize(R.dimen.ip_text_x_position), getResources().getDimensionPixelSize(R.dimen.ip_text_y_position), getTextPaint());
        canvas.restore();
        return newBitmap;
    }
}
