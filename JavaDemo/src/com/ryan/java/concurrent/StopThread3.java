package com.ryan.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 比StopThread2更简洁，性能更好的StopThread
 * 使用volatile修饰符
 * volatile修饰符不执行互斥访问，但它可以保证任何一个线程在读取该域的时候都将看到最近刚刚被写入的值
 * 
 */
public class StopThread3 {

    private static volatile boolean stopRequested;
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
        Thread backgroundThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while(!stopRequested){
					i++;
				}
			}
		});
        backgroundThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
	}

}
