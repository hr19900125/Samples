package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * RxJava 创建操作
 */
public class ObservableCreateOperatorsActivity extends BaseActivity {

    @Override
    protected void click() {
        create();
        doOnNext();
        defer();
//        interval();
//        range();
        repeat();
        repeatWhen();
        timer();
    }

    /**
     * create方法默认不在任何特定的调度器上执行，默认在当前线程执行
     */
    private void create() {
        printlnToTextView("create-------------------------------");
        printlnToTextView("create threadId : " + Thread.currentThread().getId());
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                printlnToTextView("call threadId : " + Thread.currentThread().getId());
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
                printlnToTextView("onCompleted threadId : " + Thread.currentThread().getId());
                printlnToTextView("Sequence complete.");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView("onError threadId : " + Thread.currentThread().getId());
                printlnToTextView("Error: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                printlnToTextView("onNext threadId : " + Thread.currentThread().getId());
                printlnToTextView("Next: " + integer);
            }
        });
    }

    /**
     * 可以认为就是一个回调方法
     */
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

    /**
     * http://www.jianshu.com/p/c83996149f5b
     */
    private void defer() {
        //defer 保证回调中的方法在订阅之后才开始执行，这样可以保证代码中的值都是最新的
        //而just()，from()都是先执行了方法中的代码，已经存储了事件列表
        //而Observable.create()和just，from不同
        printlnToTextView("defer-just------------------------------");
        //结果为null
        class SomeType {
            private String value;

            public void setValue(String value) {
                this.value = value;
            }

            public Observable<String> valueObservableWithJust() {
                return Observable.just(value);
            }

            public Observable<String> valueObservableWithCreate() {
                return Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext(value);
                        subscriber.onCompleted();
                    }
                });
            }

            public Observable<String> valueObservableWithDefer() {
                return Observable.defer(new Func0<Observable<String>>() {
                    @Override
                    public Observable<String> call() {
                        return Observable.just(value);
                    }
                });
            }
        }

        SomeType someType = new SomeType();
        Observable<String> value = someType.valueObservableWithJust();
        someType.setValue("hi");
        value.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });

        printlnToTextView("defer-create------------------------------");
        //结果为aaaaa,所以可以得出结果是create并不会保存值
        SomeType someType1 = new SomeType();
        Observable<String> value1 = someType1.valueObservableWithCreate();
        someType1.setValue("aaaaa");
        value1.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });

        printlnToTextView("defer------------------------------");
        SomeType someType2 = new SomeType();
        Observable<String> value2 = someType2.valueObservableWithDefer();
        someType2.setValue("bbbb");
        value2.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView(s);
            }
        });
    }

    /**
     * 创建一个按固定时间间隔发射整数序列的Observable
     * Interval操作符返回一个Observable，它按固定的时间间隔发射一个无限递增的整数序列
     * <p/>
     * 就是隔段时间就发送一个整数事件
     */
    private void interval() {
        printlnToTextView("interval------------------------------");
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                printlnToTextView("CurrentTime:" + System.currentTimeMillis() + ", value: " + aLong);
            }
        });
    }

    /**
     * 发送一个[n,n+m-1]范围内的整数序列
     */
    private void range() {
        printlnToTextView("range------------------------------");
        Observable.range(5, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("CurrentTime:" + System.currentTimeMillis() + ", value: " + integer);
            }
        });
    }

    /**
     * 创建一个发射特定数据重复多次的Observable
     */
    private void repeat() {
        printlnToTextView("repeat------------------------------thread id:" + Thread.currentThread().getId());
        Observable.range(2, 5).repeat(2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("repeat : value = " + integer);
            }
        });
    }

    /**
     * 创建一个重复发射指定数据或数据序列的Observable，它依赖于另一个Observable发射的数据
     */
    private void repeatWhen() {
        printlnToTextView("repeatWhen------------------------------");
        Observable.range(2, 3).repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Void> observable) {
                return Observable.timer(5, TimeUnit.SECONDS);
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("repeatWhen : value = " + integer);
            }
        });
    }

    /**
     * timer 返回一个Observable，它在延迟一段给定的时间后发射一个简单的数字0。
     */
    private void timer() {
        printlnToTextView("timer------------------------------");
        Observable.timer(3, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                printlnToTextView("time value " + aLong);
            }
        });
    }
}
