package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Observable Operators
 * <p/>
 * 1.map
 * 将一个事件转化为另外一个事件，即映射
 * 2.flatMap
 * flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable，可以认为是处理上一个Observable的结果并做处理，处理的结果作为新的Observable供Subscribe订阅
 */
public class ObservableOperatorsActivity extends BaseActivity {

    @Override
    protected void click() {
        map();
        flatMap();
    }

    private void map() {
        printlnToTextView("map-------------------------------");
        Observable.from(new String[]{"hello world 1", "hello world 2", "hello world 3"}).map(new Func1<String, String>() {

            @Override
            public String call(String s) {
                printlnToTextView(s);
                return s + " map";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }

    private void flatMap() {
        printlnToTextView("flatMap-------------------------------");
        Observable.from(new String[]{"hello world 1", "hello world 2", "hello world 3"}).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                printlnToTextView(s);
                return Observable.just(s + " flatMap");
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }
}
