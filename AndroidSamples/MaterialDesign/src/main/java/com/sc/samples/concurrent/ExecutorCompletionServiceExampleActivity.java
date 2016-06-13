package com.sc.samples.concurrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorCompletionService Example
 * http://www.jianshu.com/p/cfda708a3478
 * <p/>
 * ExecutorCompletionService主要用与管理异步任务 (有结果的任务, 任务完成后要处理结果)
 */
public class ExecutorCompletionServiceExampleActivity extends BaseActivity {

    @Override
    protected void click() {
        test();
    }

    private void test() {
        ExecutorService exeService = Executors.newCachedThreadPool();
        //这里ExecutorCompletionService<V> 的泛型的V，指的是返回的结果类型
        final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(exeService);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    printlnToTextView(mResultTextView, "call Thread name[" + Thread.currentThread().getName() + "]");
                    Thread.currentThread().sleep((long) (Math.random() * 1000));
                    return Thread.currentThread().getName();
                }
            });
        }

        Executors.newSingleThreadExecutor().submit(new Runnable() {

            private int cnt = 10;

            @Override
            public void run() {
                while (cnt-- > 0) {
                    try {
                        String result = completionService.take().get();
                        printlnToTextView(mResultTextView, "Result Thread name[" + result + "]");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
