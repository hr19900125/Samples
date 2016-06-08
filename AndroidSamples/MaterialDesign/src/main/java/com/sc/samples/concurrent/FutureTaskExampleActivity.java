package com.sc.samples.concurrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * http://uule.iteye.com/blog/1539084
 * FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
 * <p/>
 * 单独使用Runnable时：
 * 无法获得返回值
 * <p/>
 * 单独使用Callable时：
 * 无法在新线程中(new Thread(Runnable r))使用，只能使用ExecutorService
 * Thread类只支持Runnable
 * <p/>
 * FutureTask：
 * 实现了Runnable和Future，所以兼顾两者优点
 * 既可以使用ExecutorService，也可以使用Thread
 */
public class FutureTaskExampleActivity extends BaseActivity {

    private Button mButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);
        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.btn);
        mResultTextView = (TextView) findViewById(R.id.textview);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new CalFutureTask(new CalculationCallable(100)));
        Integer result = new Integer(0);
        executorService.submit(new CalFutureTask(new CalculationRunnable(100), result));
        executorService.shutdown();
    }

    /**
     * Callable 的泛型参数V， 表示的是call 的返回结果
     */
    private class CalculationCallable implements Callable<Integer> {

        private int count;

        public CalculationCallable(int cnt) {
            count = cnt;
        }


        @Override
        public Integer call() throws Exception {
            printlnToTextView(mResultTextView, "call method invoked in thread:" + Thread.currentThread().getId());
            int sum = 0;
            for (int i = 1; i <= count; i++) {
                sum += i;
            }
            return sum;
        }
    }

    private class CalculationRunnable implements Runnable {

        private int count;

        public CalculationRunnable(int count) {
            this.count = count;
        }

        @Override
        public void run() {
            printlnToTextView(mResultTextView, "run method invoked in thread:" + Thread.currentThread().getId());
            int sum = 0;
            for (int i = 1; i <= count; i++) {
                sum += i;
            }
        }
    }

    /**
     * FutureTask 有个泛型参数V， 表示的是返回结果
     * done 方法是在call/run执行之后再执行的
     * 通过源码分析到，done在任务完成，取消或抛异常的时候都会执行
     */
    private class CalFutureTask extends FutureTask<Integer> {

        private static final int TYPE_CALLABLE = 1;
        private static final int TYPE_RUNNABLE = 2;
        private int type = 1;

        public CalFutureTask(Callable<Integer> callable) {
            super(callable);
            type = 1;

        }

        public CalFutureTask(Runnable r, Integer result) {
            super(r, result);
            type = 2;
        }

        @Override
        protected void done() {
            printlnToTextView(mResultTextView, "done method invoked in thread:" + Thread.currentThread().getId());
            try {
                Integer result = get();
                if (type == TYPE_CALLABLE) {
                    printlnToTextView(mResultTextView, "callable result = " + result);
                } else {
                    printlnToTextView(mResultTextView, "runnable result = " + result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
    }
}
