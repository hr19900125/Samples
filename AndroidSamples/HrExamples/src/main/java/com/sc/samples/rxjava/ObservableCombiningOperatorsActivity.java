package com.sc.samples.rxjava;

import com.sc.samples.BaseActivity;

import rx.Observable;
import rx.functions.Action1;

/**
 *
 */
public class ObservableCombiningOperatorsActivity extends BaseActivity{

    @Override
    protected void click() {
        startWith();
    }

    private void startWith() {
        Observable.just(1,2,3).startWith(-1,-2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printlnToTextView("startWith value : " + integer);
            }
        });
    }


}
