package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 使用RxJava 提供的Action来订阅（subscribe）
 */
public class SubscribeActionActivity extends BaseActivity {

    @Override
    protected void click() {
        test();
    }

    private void test() {
        Observable<String> ob = Observable.from(new String[]{"hello world 1", "hello world 2", "hello world 3"});
        ob.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                //等同与onNext
                printlnToTextView("onNext:" + s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //等同与onError
                printlnToTextView("onError");
            }
        }, new Action0() {
            @Override
            public void call() {
                //等同于onCompleted
                printlnToTextView("onCompleted");
            }
        });
    }
}
