package com.sc.samples.concurrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable类似Runnable， 区别是Runnable不能返回结果，而Callable可以，并且Callable可以返回异常，常常使用Future与Callable结合使用
 * Future模式：http://openhome.cc/Gossip/DesignPattern/FuturePattern.htm
 */
public class CallableAndFutureExampleActivity extends BaseActivity {

    @Override
    protected void click() {
        begin();
    }

    private void begin() {
        mResultTextView.setText("");

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return new Random().nextInt(100);
            }
        };

        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        /**
         * 将callable提交给ExecutorService执行后返回Future对象，可以通过该Future对象获取callable的结果，判断callable的状态
         * Future 接口：
         * 1.boolean cancel(boolean mayInterruptIfRunning); 试图取消该任务执行
         * 2.boolean isCancelled(); 获取该人物是否取消的状态，如果在任务正常完成前将其取消，则返回 true
         * 3.boolean isDone(); 如果任务完成，则返回true
         * 4.V get() throws InterruptedException, ExecutionException; 获取任务的执行结果，假如任务还未完成，则阻塞当前线程
         * 5.V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException; 和4雷同，只是多了时间限制
         *
         * tips:
         * 假如Future cancel掉的话，再判断isDone返回的是true，而future.get() 会抛java.util.concurrent.CancellationException异常
         */
        final Future<Integer> future = threadPool.submit(callable);
        mResultTextView.post(new Runnable() {
            @Override
            public void run() {
                getResult(future);
            }
        });

        mResultTextView.post(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                getResult(future);
            }
        });

        mResultTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                getResult(future);
            }
        }, 2000);
    }

    private void getResult(Future<Integer> future) {
        printlnToTextView(mResultTextView, "callable is cancel ? : " + future.isCancelled());
        if (future.isDone() && !future.isCancelled()) {
            try {
                printlnToTextView(mResultTextView, "callable result : " + String.valueOf(future.get().intValue()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            printlnToTextView(mResultTextView, "callable isDone : " + future.isDone());
        }
    }
}
