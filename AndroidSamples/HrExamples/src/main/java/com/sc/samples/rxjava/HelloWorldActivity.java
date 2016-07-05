package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

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
