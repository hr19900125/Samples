package com.sc.samples.rxjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import rx.Observable;
import rx.Subscriber;

/**
 * Creating an Observable via the create( ) method
 */
public class ObservableCreateActivity extends BaseActivity {

    @Override
    protected void click() {
        create();
    }

    private void create() {
        Observable<String> ob = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world! 1"); //1
//                String str = null; //这两行用来测试加入在call方法里面抛异常了之后的流程，流程是，call方法这里之后的代码都不会执行，sub的onError会接收到异常并调用
//                str.charAt(0);
                subscriber.onNext("hello world! 2"); //2
                //下面用来测试onError被调用之后，之后的方法会不会执行，经测试之执行了1，2，并且会执行sub的onError方法
//                subscriber.onError(new RuntimeException()); //3
                subscriber.onNext("hello world! 3"); //4
                subscriber.onCompleted(); //5 onCompleted方法假如不调用的话，下面的还会执行，问题：不调用onCompleted的后果？
                //下面用来测试onCompleted 调用之后，onNext是否还生效, 经测试验证是不生效的，下面的onNext没有执行。
                subscriber.onNext("hello world! 4"); //6
            }
        });

        Subscriber<String> sub = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                printlnToTextView(mResultTextView, "Subscriber call onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView(mResultTextView, "Subscriber call onError");
            }

            @Override
            public void onNext(String s) {
                printlnToTextView(mResultTextView, s);
            }
        };

        //当Observable 与Subscriber通过subscribe完成订阅之后，Observable里面的call方法马上就会执行
        ob.subscribe(sub);
    }
}
