package com.ryan.java.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadWaitAndNotifyDemo2 {

    public static class MyObject implements Runnable {
        private Monitor monitor;

        public MyObject(Monitor monitor) {
          this.monitor = monitor;
        }

        public void run() {
          try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("i'm going.");
            monitor.gotMessage();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    }
    
    public static class Monitor implements Runnable {
        private volatile boolean go = false;

        public synchronized void gotMessage() throws InterruptedException {
          go = true;
          notify();
        }

        public synchronized void watching() throws InterruptedException {
          while (go == false)
            wait();
          System.out.println("He has gone.");
        }

        public void run() {
          try {
            watching();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
    }
    
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        MyObject o = new MyObject(monitor);
        new Thread(o).start();
        new Thread(monitor).start();
      }
}
