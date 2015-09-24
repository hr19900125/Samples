package com.ryan.java.concurrent;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	
	public static void main(String[] args) throws InterruptedException {
		final AtomicInteger value = new AtomicInteger(10);
		assertEquals(value.compareAndSet(1, 2), false);
		assertEquals(value.get(), 10);
		assertTrue(value.compareAndSet(10, 3));
		assertEquals(value.get(), 3);
		value.set(0);
		
		assertEquals(value.incrementAndGet(), 1);
		assertEquals(value.getAndAdd(2), 1);
		assertEquals(value.getAndSet(5), 3);
		assertEquals(value.get(), 5);
		
		final int threadSize = 10;
		Thread[] ts = new Thread[threadSize];
		for(int i=0; i<threadSize;i ++) {
			ts[i] = new Thread(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(value.incrementAndGet());
				}
			});
		}
		
		for(Thread t : ts) {
			t.start();
		}
		
		for(Thread t : ts) {
			t.join();
		}
		
		assertEquals(value.get(), 5+threadSize);
	}
	
}
