package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

import java.io.ByteArrayOutputStream;

/**
 *
 */
public class SaveImageInPreferenceAct extends Activity{

    private Button mSaveBtn, mRestoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_image_in_preference);

        mSaveBtn = (Button) findViewById(R.id.save_image_in_pref);
        mRestoreBtn = (Button) findViewById(R.id.restore_image_in_pref);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                setBitmapToPreference(SaveImageInPreferenceAct.this, bitmap, "save_image_in_pref", "image_pref");
            }
        });

        mRestoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBitmapFromPreference(SaveImageInPreferenceAct.this, "save_image_in_pref", "image_pref");
            }
        });

    }

    private static void setBitmapToPreference(Context context, Bitmap bitmap, String name, String PREFS_FILE_NAME) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        Common.setStringPrefrences(context, name, temp, PREFS_FILE_NAME);
    }

    private static Bitmap getBitmapFromPreference(Context context, String name, String PREFS_FILE_NAME) {
        try {
            String imageString = Common.getStringPrefrences(context, name, PREFS_FILE_NAME);
            byte[] encodeByte = Base64.decode(imageString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
