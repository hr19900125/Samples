
package com.ryan.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    // 创建一个计算任务，返回累加结果，构造器的参数是上界
    static class SumCaller implements Callable<Long> {
        private Integer count;

        public SumCaller(Integer count) {
            this.count = count;
        }

        public Long call() throws Exception {
            long sum = 0;
            for (int i = 0; i < count; i++) {
                sum += i;
            }
            Thread.sleep(3000);
            return sum;
        }
    }

    private static Integer COUNT = 1000000000;

    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        SumCaller caller = new SumCaller(COUNT);
        FutureTask<Long> task = new FutureTask<Long>(caller);

        Thread thread = new Thread(task);
        thread.start();

        long sum = task.get();
        System.out.println("sum from 1 to " + COUNT + " result = " + sum);

    }
}
