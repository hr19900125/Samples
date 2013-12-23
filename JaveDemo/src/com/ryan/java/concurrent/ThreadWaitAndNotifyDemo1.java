package com.ryan.java.concurrent;

import java.util.concurrent.TimeUnit;

public class ThreadWaitAndNotifyDemo1 {

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

        public void gotMessage() throws InterruptedException {
          go = true;
        }

        public void watching() {
          while (go == false)
            ;
          System.out.println("He has gone.");
        }

        public void run() {
          watching();
        }
    }
    
    public static void main(String[] args) {
        Monitor monitor = new Monitor();
        MyObject o = new MyObject(monitor);
        new Thread(o).start();
        new Thread(monitor).start();
      }
}
