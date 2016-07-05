package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 *
 */
public class ObservableFilteringOperatorsActivity extends BaseActivity {

    @Override
    protected void click() {
//        debounce();
//        distinct();
//        elementAt();
//        filter();
//        first();
//        ignoreElements();
//        last();
//        sample();
//        skip();
        skipLast();
        take();
        takeLast();
    }

    /**
     * debounce操作符
     * <p/>
     * debounce操作符对源Observable每产生一个结果后，如果在规定的间隔时间内没有别的结果产生，则把这个结果提交给订阅者处理，否则忽略该结果。
     * <p/>
     * 值得注意的是，如果源Observable产生的最后一个结果后在规定的时间间隔内调用了onCompleted，那么通过debounce操作符也会把这个结果提交给订阅者。
     * <p/>
     * 个人理解：就是假如发送A-(time1)-B-(time2)-C，A与B的发送时间间隔为time1，则debounce(int, TimeUnit)的时间为time，相当于过滤掉time1或time2小于time的之前的事件
     */
    private void debounce() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 1; i < 10; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.newThread()).debounce(400, TimeUnit.MILLISECONDS).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("Next:" + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                printlnToTextView("Error:" + throwable);
            }
        }, new Action0() {
            @Override
            public void call() {
                printlnToTextView("onCompleted");
            }
        });
    }

    /**
     * Distinct的过滤规则是：只允许还没有发射过的数据项通过。
     */
    private void distinct() {
        Observable.just(1, 2, 2, 1, 3, 3).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("distinct number: " + integer);
            }
        });
    }

    /**
     * 只发射第N项数据
     */
    private void elementAt() {
        Observable.just(1, 2, 3, 4, 5, 6).elementAt(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("elementAt value: " + integer);
            }
        });
    }

    /**
     * 把不符合条件的过滤掉,留下符合条件的事件
     */
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

    /**
     * 只发射第一项（或者满足某个条件的第一项）数据
     */
    private void first() {
        Observable.just(1, 2, 3, 4).first().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("first value : " + integer);
            }
        });
    }

    /**
     * 不发射任何数据，只发射Observable的终止通知
     */
    private void ignoreElements() {
        Observable.just(1, 2, 3).ignoreElements().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("ignoreElements onNext : " + integer);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                printlnToTextView("ignoreElements onError : " + throwable);
            }
        }, new Action0() {
            @Override
            public void call() {
                printlnToTextView("ignoreElements onCompleted");
            }
        });
    }

    /**
     * 只发射最后一项（或者满足某个条件的最后一项）数据
     */
    private void last() {
        Observable.just(1, 2, 3, 4).last().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("last value : " + integer);
            }
        });
    }

    /**
     * sample操作符定期扫描源Observable产生的结果，在指定的时间间隔范围内对源Observable产生的结果进行采样
     */
    private void sample() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    //前8个数字产生的时间间隔为1秒，后一个间隔为3秒
                    for (int i = 1; i < 9; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(1000);
                    }
                    Thread.sleep(2000);
                    subscriber.onNext(9);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread()).sample(2200, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                printlnToTextView("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView("onError : " + e);
            }

            @Override
            public void onNext(Integer integer) {
                printlnToTextView("onNext : " + integer);
            }
        });
    }

    /**
     * 抑制Observable发射的前N项数据
     */
    private void skip() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).skip(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("skip value : " + integer);
            }
        });
    }

    /**
     * 抑制Observable发射的后N项数据
     */
    private void skipLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).skipLast(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("skipLast value : " + integer);
            }
        });
    }

    /**
     * 取前面几个事件
     */
    private void take() {
        printlnToTextView("take-------------------------------");
        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).take(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
            }
        });
    }

    /**
     *
     */
    private void takeLast() {
        printlnToTextView("takeLast-------------------------------");
        Observable.from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).takeLast(4).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView(String.valueOf(integer));
            }
        });
    }

}
