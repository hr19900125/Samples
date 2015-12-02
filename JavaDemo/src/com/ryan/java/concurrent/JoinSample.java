package com.ryan.java.concurrent;

public class JoinSample {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Thread main start ");
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread t finish");
			}
		});
		t.start();
		t.join();
		
		System.out.println("Thread main continue");
		
	}
	
}
