package com.sc.samples.concurrent;

import com.sc.samples.BaseActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue 使用example
 */
public class BlockingQueueExampleActivity extends BaseActivity {

    private BlockingQueue<String> mQueue = new LinkedBlockingQueue<>(3);

    @Override
    protected void click() {
        begin();
    }

    private void begin() {
        mResultTextView.setText("");

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.submit(new PutRunnable(mQueue, i));
        }

        executor.submit(new GetRunnable(mQueue));
        executor.shutdown();
    }

    private class PutRunnable implements Runnable {

        private BlockingQueue<String> queue;
        private int id;

        public PutRunnable(BlockingQueue<String> q, int id) {
            this.queue = q;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                queue.put(String.valueOf(id));
                printlnToTextView(mResultTextView, "[" + id + "] put into queue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class GetRunnable implements Runnable {

        private BlockingQueue<String> queue;

        public GetRunnable(BlockingQueue<String> q) {
            queue = q;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    if (queue.isEmpty()) break;
                    String str = queue.take();
                    printlnToTextView(mResultTextView, str + " has take!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
