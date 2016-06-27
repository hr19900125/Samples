package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava 变换操作
 */
public class ObservableTransformOperatorsActivity extends BaseActivity {
    @Override
    protected void click() {
        map();
        flatMap();
    }

    /**
     * 将一个事件转化为另外一个事件，即映射
     */
    private void map() {
        printlnToTextView("map-------------------------------");
        Observable.just("hello world").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                printlnToTextView(s);
                return 5428;
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
                return String.valueOf(integer);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }

    /**
     * flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable，可以认为是处理上一个Observable的结果并做处理，处理的结果作为新的Observable供Subscribe订阅
     */
    private void flatMap() {
        printlnToTextView("flatMap-------------------------------");
        List<String> s = Arrays.asList("hello world 1", "hello world 2", "hello world 3");
        Observable.just(s).flatMap(new Func1<List<String>, Observable<String>>() {
            @Override
            public Observable<String> call(List<String> strings) {
                return Observable.from(strings);
            }
        }).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                printlnToTextView("---");
                printlnToTextView(s);
                return Observable.just("hi !" + s);
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView("---");
                printlnToTextView(s);
            }
        });
    }

    /**
     *
     */
    private void buffer(){
        printlnToTextView("buffer-------------------------------");
//        Observable.
    }
}
