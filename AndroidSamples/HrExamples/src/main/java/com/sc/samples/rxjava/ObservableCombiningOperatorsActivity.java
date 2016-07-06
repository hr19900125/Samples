package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

/**
 *
 */
public class ObservableCombiningOperatorsActivity extends BaseActivity {

    @Override
    protected void click() {
//        startWith();
//        merge();
//        mergeDelayError();
        combineLatest();
    }

    /**
     * 在数据序列的开头插入一条指定的项
     */
    private void startWith() {
        Observable.just(1, 2, 3).startWith(-1, -2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("startWith value : " + integer);
            }
        });
    }

    /**
     * 合并多个Observables的发射物
     * <p/>
     * 使用Merge操作符你可以将多个Observables的输出合并，就好像它们是一个单个的Observable一样。
     * <p/>
     * Merge可能会让合并的Observables发射的数据交错（有一个类似的操作符Concat不会让数据交错，它会按顺序一个接着一个发射多个Observables的发射物）。
     */
    private void merge() {
        Observable<Integer> odds = Observable.just(1, 3, 5).subscribeOn(Schedulers.newThread());
        Observable<Integer> evens = Observable.just(2, 4, 6);

        Observable.merge(odds, evens).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                printlnToTextView("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                printlnToTextView("onError:" + e);
            }

            @Override
            public void onNext(Integer integer) {
                printlnToTextView("onNext:" + integer);
            }
        });
    }

    /**
     * 当发生onError时，会等待其他Observable将数据发射完，然后才将onError发送个观察者
     */
    private void mergeDelayError() {
        Observable<Integer> odds = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });

        Observable<Integer> evens = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(subscriber.isUnsubscribed())return;
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                    if (i == 3) subscriber.onError(new RuntimeException());
                }
                subscriber.onCompleted();
            }
        });

        Observable.mergeDelayError(odds, evens).subscribe(new Subscriber<Integer>() {
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
     * 当两个Observables中的任何一个发射数据时，使用一个函数结合每个Observable发射的最近数据项，并且基于这个函数的结果发射出一个新的数据。
     * 好比工厂的流水线，下面一件产品需要两个流水线上的零件组合，流水线1的工人生产了一个零件，只有等流水线2的工人生产了另一个零件的时候，才能组合成一个产品；
     * 流水线2的工人速度快一些，很快生产了第二个零件，这时候流水线1的工人还没有生产第二个零件，流水线2的工人就会拿流水线1的第一个零件将就用着合成第二个产品。
     * 这个例子只是方便理解，我们假设零件可以复用。
     */
    private void combineLatest() {
        Observable.combineLatest(getObservable("one->"), getObservable("two->"), getObservable("three->"), new Func3<String, String, String, String>() {
            //使用一个函数结合它们最近发射的数据，然后发射这个函数的返回值
            @Override
            public String call(String s, String s2, String s3) {
                return s + "," + s2 + "," + s3;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                printlnToTextView("combineLatest:"+s);
            }
        });
    }

    private Observable<String> getObservable(final String name) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                if(name.contains("-")) {
                    for (int i = 1;i <= 3; i++) {
                        printlnToTextView(name + i);
                        subscriber.onNext(name + i);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }


}
