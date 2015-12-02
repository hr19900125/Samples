package com.ryan.java.concurrent;

public class InterruptedSample {
	
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
						break;
					}
				}
			}
		});
		t.start();
		
		Thread.sleep(3000);
		t.interrupt();
		System.out.println("main finish");
	}

}
