package com.ryan.java.concurrent;

public class ThreadStateTest {

	private static void newState() {
		Thread t = new Thread();
		System.out.println("new State : " + t.getState());
	}

	private static void runnableState() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					// System.out.println(i);
				}
			}
		});
		t.start();
		System.out.println("runnable State : " + t.getState());
	}

	private static void blockedState() {
		final Object lock = new Object();
		Runnable r = new Runnable() {

			@Override
			public void run() {
				int count = 0;
				for (int i = 0; i < Integer.MAX_VALUE; i++) {
					synchronized (lock) {
//						System.out.println(Thread.currentThread().getName()
//								+ " thread State : "
//								+ Thread.currentThread().getState());
						count ++;
					}
				}
			}
		};
		Thread t1 = new Thread(r);
		t1.setName("t1");
		Thread t2 = new Thread(r);
		t2.setName("t2");
		t1.start();
		t2.start();
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			System.out.println("thread State t1 = " + t1.getState() + ", t2 = "
					+ t2.getState());
		}
	}

	public static void main(String[] args) {
		newState();
		runnableState();
		blockedState();
	}

}
