package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

public class BlurEffectActivity extends Activity {

    private ImageView ivBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur_effect);

        ivBlur = (ImageView) findViewById(R.id.blur_effect_iv_blur);

        ivBlur.setImageDrawable(Common.blurEffectsOnDrawable(this, R.drawable.ic_launcher, 5));
    }

}
