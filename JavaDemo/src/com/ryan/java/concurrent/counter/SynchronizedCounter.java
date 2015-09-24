package com.ryan.java.concurrent.counter;
/**
 * 同步的计数器类
 *
 */
public class SynchronizedCounter {

	private int value;
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int increment() {
		return ++value;
	}
	
	public synchronized int decrement() {
		return --value;
	}
	
}
