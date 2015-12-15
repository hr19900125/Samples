package com.ryan.java.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 修正StopThread出现的线程没有同步导致的问题 
 *
 */
public class StopThread2 {

	private static boolean stopRequested;
	
	private static synchronized void requestStop(){
		stopRequested = true;
	}
	
	private static synchronized boolean stopRequested(){
		return stopRequested;
	}
	
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
				while(!stopRequested()){
					i++;
				}
			}
		});
        backgroundThread.start();
        
        TimeUnit.SECONDS.sleep(1);
        requestStop();
        
	}

}
