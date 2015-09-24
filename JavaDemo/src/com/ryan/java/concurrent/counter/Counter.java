package com.ryan.java.concurrent.counter;
/**
 * 使用同步的线程安全的计数器
 *
 */
public class Counter {

	private long value = 0;
	
	public synchronized long getValue() {
		return value;
	}
	
	public synchronized long increment() {
		return ++value;
	}
	
}
