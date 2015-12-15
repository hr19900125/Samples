package com.ryan.java.concurrent.counter;

/**
 * 说明比较并交换的行为（而不是性能）的代码
 *
 */
public class SimulatedCAS {
	
	private int value;
	
	public synchronized int getValue() {
		return value;
	}
	
	public synchronized int compareAndSwap(int expectedValue, int newValue) {
		int oldValue = value;
		if(value == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}
}
