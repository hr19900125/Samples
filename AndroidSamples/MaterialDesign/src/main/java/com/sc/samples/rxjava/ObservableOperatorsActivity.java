package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * https://www.gitbook.com/book/mcxiaoke/rxdocs
 * <p/>
 * Observable Operators
 * 0.create
 * create方法默认不在任何特定的调度器上执行。
 * <p/>
 * 1.map
 * 将一个事件转化为另外一个事件，即映射
 * <p/>
 * 2.flatMap
 * flatMap()接收一个Observable的输出作为输入，同时输出另外一个Observable，可以认为是处理上一个Observable的结果并做处理，处理的结果作为新的Observable供Subscribe订阅
 * <p/>
 * http://www.jianshu.com/p/88779bda6691 map和flatMap的区别就是返回值，相对来说flatMap可以做更多的事情
 * 3.filter
 * 把不符合条件的过滤掉,留下符合条件的事件
 * 4.take
 * 取前面几个事件
 * 5.doOnNext
 * 可以认为就是一个回调方法
 */
public class ObservableOperatorsActivity extends BaseActivity {

    @Override
    protected void click() {
        create();
        map();
        flatMap();
        filter();
        take();
        doOnNext();
    }

    private void create() {
        printlnToTextView("create-------------------------------");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                try {
                    if (!subscriber.isUnsubscribed()) {
                        for (int i = 0; i < 5; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                printlnToTextView("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                printlnToTextView("Next: " + integer);
            }
        });
    }

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

    private void filter() {
        printlnToTextView("filter-------------------------------");
        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 5;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
            }
        });
    }

    private void take() {
        printlnToTextView("take-------------------------------");
        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).take(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
            }
        });
    }

    private void doOnNext() {
        printlnToTextView("doOnNext-------------------------------");
        Observable.from(new Integer[]{1, 2, 3, 4}).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(integer + " + 10 = " + (integer + 10));
            }
        }).take(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
            }
        });
    }
}
