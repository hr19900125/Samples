package com.ryan.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 并发编程例子，证明线程同步的问题
 */
public class StopThread {

	private static boolean stopRequested;
	
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
