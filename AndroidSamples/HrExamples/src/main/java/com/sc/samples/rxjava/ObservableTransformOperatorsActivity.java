package com.sc.samples.rxjava;

import android.os.SystemClock;

import com.sc.samples.BaseActivity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

/**
 * RxJava 变换操作
 */
public class ObservableTransformOperatorsActivity extends BaseActivity {
    @Override
    protected void click() {
//        map();
//        flatMap();
//        buffer();
//        groupBy();
//        scan();
        window();
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
    private void buffer() {
        printlnToTextView("buffer(int)-------------------------------");
        Observable.range(1, 10).buffer(3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                printlnToTextView("####");
                for (int i = 0; i < integers.size(); i++) {
                    printlnToTextView("" + integers.get(i));
                }
            }
        });

        printlnToTextView("buffer(int, int) count > skip-------------------------------");
        Observable.range(1, 10).buffer(3, 2).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                printlnToTextView("####");
                for (int i = 0; i < integers.size(); i++) {
                    printlnToTextView("" + integers.get(i));
                }
            }
        });

        printlnToTextView("buffer(int, int) count < skip-------------------------------");
        Observable.range(1, 10).buffer(2, 3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                printlnToTextView("####");
                for (int i = 0; i < integers.size(); i++) {
                    printlnToTextView("" + integers.get(i));
                }
            }
        });

        printlnToTextView("buffer(int, TimeUnit) 周期性发送buffer-------------------------------");
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                while (true) {
                    subscriber.onNext("消息" + SystemClock.elapsedRealtime());
                    SystemClock.sleep(2000);//每隔2s发送消息
                }

            }
        }).subscribeOn(Schedulers.io()).
                buffer(3, TimeUnit.SECONDS).//每隔3秒 取出消息
                subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
                printlnToTextView("-----------------onCompleted:");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView("----------------->onError:");
            }

            @Override
            public void onNext(List<String> strings) {
                printlnToTextView("----------------->onNext:" + strings);
            }
        });
    }

    private void groupBy() {
        printlnToTextView("groupBy-------------------------------");
        Observable.interval(1, TimeUnit.SECONDS).take(10).groupBy(new Func1<Long, Long>() {
            @Override
            public Long call(Long aLong) {
                return aLong % 4;
            }
        }).subscribe(new Action1<GroupedObservable<Long, Long>>() {
            @Override
            public void call(final GroupedObservable<Long, Long> result) {
                result.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        printlnToTextView("key:" + result.getKey() + ",value:" + aLong);
                    }
                });
            }
        });
    }

    /**
     * Scan操作符对原始Observable发射的第一项数据应用一个函数，然后将那个函数的结果作为自己的第一项数据发射。它将函数的结果同第二项数据一起填充给这个函数来产生它自己的第二项数据。它持续进行这个过程来产生剩余的数据序列。这个操作符在某些情况下被叫做accumulator。
     */
    private void scan() {
        printlnToTextView("scan-------------------------------");
        Observable.just(1, 2, 3, 4, 5).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer sum, Integer item) {
                return sum + item;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer sum) {
                printlnToTextView("sum = " + sum);
            }
        });
    }

    /**
     * window操作符非常类似于buffer操作符，区别在于buffer操作符产生的结果是一个List缓存，而window操作符产生的结果是一个Observable，订阅者可以对这个结果Observable重新进行订阅处理。
     */
    private void window() {
        printlnToTextView("window-------------------------------");
        Observable.interval(1, TimeUnit.SECONDS).take(12).window(3).subscribe(new Action1<Observable<Long>>() {
            @Override
            public void call(Observable<Long> observable) {
                printlnToTextView("divide ---");
                observable.subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        printlnToTextView(String.valueOf(aLong));
                    }
                });
            }
        });
    }
}
