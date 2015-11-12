package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class ChooseProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_profile);
        final ChooseProfileActivity mContext = ChooseProfileActivity.this;

        findViewById(R.id.silent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.chooseProfile(mContext, 0);
            }
        });

        findViewById(R.id.vibrate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.chooseProfile(mContext, 1);
            }
        });

        findViewById(R.id.normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.chooseProfile(mContext, 2);
            }
        });
    }

}
