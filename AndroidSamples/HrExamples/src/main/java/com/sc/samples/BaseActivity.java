package com.sc.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by huangrui on 16-6-5.
 */
public abstract class BaseActivity extends Activity {

    protected Button mButton;
    protected TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn);
        mResultTextView = (TextView) findViewById(R.id.textview);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    protected abstract void click();

    protected void printlnToTextView(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultTextView.append(text + "\n");
            }
        });
    }

    protected void printlnToTextView(final TextView textView, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(text + "\n");
            }
        });
    }

}
