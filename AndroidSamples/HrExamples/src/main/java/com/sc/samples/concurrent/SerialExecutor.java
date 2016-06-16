package com.sc.samples.concurrent;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/**
 * AsyncTask 中的SerialExecutor，使用队列保证任务逐一执行
 */
public class SerialExecutor implements Executor {

    final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
    Runnable mActive;
    Executor mExecutor;

    public SerialExecutor(Executor executor) {
        mExecutor = executor;
    }

    public synchronized void execute(final Runnable r) {
        mTasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                } finally {
                    scheduleNext();
                }
            }
        });
        if (mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((mActive = mTasks.poll()) != null) {
            mExecutor.execute(mActive);
        }
    }
}
