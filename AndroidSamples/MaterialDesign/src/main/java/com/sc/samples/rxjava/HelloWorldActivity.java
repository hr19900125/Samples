package com.sc.samples.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import rx.Observable;
import rx.functions.Action1;


/**
 * RxJava Hello world
 */
public class HelloWorldActivity extends BaseActivity {

    @Override
    protected void click() {
        hello("hello world! 1", "hello world! 2", "hello world! 3");
    }

    private void hello(String... names) {
        Observable.from(names).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }
}
