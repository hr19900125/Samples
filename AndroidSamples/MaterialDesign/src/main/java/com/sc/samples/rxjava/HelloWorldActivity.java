package com.sc.samples.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

import rx.Observable;
import rx.functions.Action1;


/**
 * RxJava Hello world
 */
public class HelloWorldActivity extends Activity {

    private Button mButton;
    private TextView mResultTextView;

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
                hello("hello world! 1", "hello world! 2", "hello world! 3");
            }
        });
    }

    private void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }

    private void printlnToTextView(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultTextView.append(text + "\n");
            }
        });
    }
}
