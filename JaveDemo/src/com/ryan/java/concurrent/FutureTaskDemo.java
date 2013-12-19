
package com.ryan.java.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        MyCall task1 = new MyCall("this is task1");
        MyCall.Result result = new MyCall.Result();
        result.setFlag("this is result");
        ExecutorService pool = Executors.newFixedThreadPool(3);
        Future f1 = new FutureTask(task1) {
            @Override
            protected void done() {
                try {
                    MyCall.Result r = (MyCall.Result) get();
                    System.out.println(r.getFlag() + " about callable");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.done();
            }
        };
        
        Future f2 = new FutureTask(new MyRun(), result) {
            @Override
            protected void done() {
                try {

                    MyCall.Result r = (MyCall.Result) get();

                    System.out.println(r.getFlag() + " about runnable");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.done();
            }
        };
        pool.execute((Runnable) f1);
        pool.execute((Runnable) f2);
    }

    public static class MyCall implements Callable {
        Result r;
        String j = "";

        MyCall() {

        }

        MyCall(String flag) {
            j = flag;
        }

        @Override
        public Result call() throws Exception {
            System.out.println("this is MyCall call");
            r = new Result();
            r.setFlag(j);
            return r;
        }

        public static class Result {
            private String flag = "";

            public String getFlag() {
                return flag;
            }

            public void setFlag(String flag) {
                this.flag = flag;
            }
        }
    }

    public static class MyRun implements Runnable {

        @Override
        public void run() {
            System.out.println("this is MyRun run");

        }

    }
}
