package com.ryan.java.concurrent;

import java.util.UUID;

/**
 * http://ifeve.com/java-threadlocal%E7%9A%84%E4%BD%BF%E7%94%A8/#more-19209
 * @author Ryan
 *
 */
public class ThreadLocalDemo {
	
    public static class MyRunnable implements Runnable {

    	private ThreadLocal<String> mThreadLocal = new ThreadLocal<String>();
    	
		@Override
		public void run() {
			// TODO Auto-generated method stub
		    mThreadLocal.set(UUID.randomUUID().toString());
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(mThreadLocal.get());
		}
    }
    
    public static void main(String[] args) {
		MyRunnable runnable = new MyRunnable();
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		thread1.start();
		thread2.start();
	}
	
}
