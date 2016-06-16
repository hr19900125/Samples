package com.sc.samples.concurrent;

import com.sc.samples.BaseActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * BlockingQueue 使用example
 * <p/>
 * http://blog.csdn.net/defonds/article/details/44021605/
 * <p/>
 * 1）数组阻塞队列 ArrayBlockingQueue（内部是数组存储元素实现）
 * ArrayBlockingQueue 类实现了 BlockingQueue 接口。
 * ArrayBlockingQueue 是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。有界也就意味着，它不能够存储无限多数量的元素。它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了(译者注：因为它是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改)。
 * ArrayBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
 * <p/>
 * 2）延迟队列 DelayQueue (可以根据Delayed接口的getDelay方法的返回值作为)
 * DelayQueue 实现了 BlockingQueue 接口。
 * DelayQueue 对元素进行持有直到一个特定的延迟到期。注入其中的元素必须实现 java.util.concurrent.Delayed 接口
 * <p/>
 * 3）链阻塞队列 LinkedBlockingQueue
 * LinkedBlockingQueue 类实现了 BlockingQueue 接口。
 * LinkedBlockingQueue 内部以一个链式结构(链接节点)对其元素进行存储。如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE 作为上限。
 * LinkedBlockingQueue 内部以 FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
 * <p/>
 * 4）具有优先级的阻塞队列 PriorityBlockingQueue
 * PriorityBlockingQueue 类实现了 BlockingQueue 接口。
 * PriorityBlockingQueue 是一个无界的并发队列。它使用了和类 java.util.PriorityQueue 一样的排序规则。你无法向这个队列中插入 null 值。
 * 所有插入到 PriorityBlockingQueue 的元素必须实现 java.lang.Comparable 接口。因此该队列中元素的排序就取决于你自己的 Comparable 实现。
 * 注意 PriorityBlockingQueue 对于具有相等优先级(compare() == 0)的元素并不强制任何特定行为。
 * 同时注意，如果你从一个 PriorityBlockingQueue 获得一个 Iterator 的话，该 Iterator 并不能保证它对元素的遍历是以优先级为序的。
 * <p/>
 * 5）同步队列 SynchronousQueue
 * SynchronousQueue 类实现了 BlockingQueue 接口。
 * SynchronousQueue 是一个特殊的队列，它的内部同时只能够容纳单个元素。如果该队列已有一元素的话，试图向队列中插入一个新元素的线程将会阻塞，直到另一个线程将该元素从队列中抽走。同样，如果该队列为空，试图向队列中抽取一个元素的线程将会阻塞，直到另一个线程向队列中插入了一条新的元素。
 * 据此，把这个类称作一个队列显然是夸大其词了。它更多像是一个汇合点。
 * <p/>
 * 6）阻塞双端队列 BlockingDeque（该接口实现LinkedBlockingDeque）
 * java.util.concurrent 包里的 BlockingDeque 接口表示一个线程安放入和提取实例的双端队列。本小节我将给你演示如何使用 BlockingDeque。
 * BlockingDeque 类是一个双端队列，在不能够插入元素时，它将阻塞住试图插入元素的线程；在不能够抽取元素时，它将阻塞住试图抽取的线程。
 * deque(双端队列) 是 "Double Ended Queue" 的缩写。因此，双端队列是一个你可以从任意一端插入或者抽取元素的队列。
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
