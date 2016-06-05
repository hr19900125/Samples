package com.sc.samples;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by huangrui on 16-6-5.
 */
public class BaseActivity extends Activity {

    protected void printlnToTextView(final TextView textView, final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(text + "\n");
            }
        });
    }

}
