package com.sc.samples.concurrent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService Example
 */
public class ExecutorServiceExampleActivity extends Activity {

    private static final int MSG_PRINT = 1;
    private Button mBeginButton;
    private TextView mResultTextView;
    private Handler mUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_PRINT) {
                String text = (String) msg.obj;
                mResultTextView.append(text + "\n");
            }
        }
    };

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     */
    private ExecutorService mSingleThreadPool = Executors.newSingleThreadExecutor();
    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     */
    private ExecutorService mFixedThreadPool = Executors.newFixedThreadPool(3);
    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    private ExecutorService mCachedThreadPool = Executors.newCachedThreadPool();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * shutdown 与 shutdownNow 的区别
         * shutdown:当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。此时，则不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。
         * 但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出。
         * shutdownNow:根据JDK文档描述，大致意思是：执行该方法，线程池的状态立刻变成STOP状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，
         * 当然，它会返回那些未执行的任务。它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，但是大家知道，这种方法的作用有限，
         * 如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。所以，ShutdownNow()并不代表线程池就一定立即就能退出，
         * 它可能必须要等待所有正在执行的任务都执行完成了才能退出。
         *
         * 区别是关于已经提交未完成任务的处理已经线程终端的处理，shutdown会继续执行并且完成所有未执行的任务，shutdownNow 会清除所有未执行的任务并且在运行线程上调用interrupt() 。
         */
//        mSingleThreadPool.shutdownNow();
        mFixedThreadPool.shutdownNow();
    }

    private void initView() {
        mBeginButton = (Button) findViewById(R.id.btn);
        mResultTextView = (TextView) findViewById(R.id.textview);

        mBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                begin();
            }
        });
    }

    private void begin() {
        mResultTextView.setText("");
        printlnToTextView("Test begin");
        for (int i = 0; i < 15; i++) {
//            mSingleThreadPool.execute(new TestRunnable(i));
            mFixedThreadPool.execute(new TestRunnable(i));
        }
        printlnToTextView("execute over");
    }

    private void printlnToTextView(String text) {
        mUiHandler.obtainMessage(MSG_PRINT, text).sendToTarget();
    }

    private class TestRunnable implements Runnable {

        private int mId;

        public TestRunnable(int id) {
            mId = id;
        }

        @Override
        public void run() {
            try {
                printlnToTextView("Work[" + mId + "] start...");
                Thread.sleep((int) (Math.random() * 5000));
                printlnToTextView("Work[" + mId + "] end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
