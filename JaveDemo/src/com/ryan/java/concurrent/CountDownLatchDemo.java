package com.ryan.java.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static long timeTasks(int nThreads , final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        
        for(int i = 0;i<nThreads ;i++){
            Thread t = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally{
                            endGate.countDown();
                        }
                    }catch(InterruptedException e){
                        System.out.println("InterruptedException ");
//                        e.printStackTrace();
                    }
                }
            });
            t.start();
        }
        
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
    
    public static void main(String[] args){
        try {
            System.out.println("time" + timeTasks(30, new Runnable() {
                int count = 0;
                
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println("Runnable "+(count++));
                }
            }));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
