package com.sc.samples.concurrent;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.R;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue 使用example
 */
public class BlockingQueueExampleActivity extends Activity {

    private static final String TAG = "BlockingQueue";
    private Button mBeginButton;
    private TextView mResultTextView;

    private BlockingQueue<String> mQueue = new LinkedBlockingQueue<>(3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_and_textview);

        initView();
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

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.submit(new PutRunnable(mQueue, i));
        }

        executor.submit(new GetRunnable(mQueue));
        executor.shutdown();
    }

    private void printlnToTextView(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultTextView.append(text + "\n");
            }
        });
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
                printlnToTextView("[" + id + "] put into queue");
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
            while(true) {
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                    if(queue.isEmpty()) break;
                    String str = queue.take();
                    printlnToTextView(str + " has take!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
