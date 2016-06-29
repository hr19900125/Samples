package com.sc.samples.widget.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sc.samples.R;

/**
 * 自定义的View，使用<declare-styleable>和obtainStyledAttributes方法
 */
public class CustomViewActivity2 extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_2);
    }
}
