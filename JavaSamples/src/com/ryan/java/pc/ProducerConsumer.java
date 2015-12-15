package com.ryan.java.pc;

import java.util.Random;

/**
 * 生产者-消费者问题 
 *
 */
public class ProducerConsumer {

	static final int N = 100; //定义缓冲区大小的常量
	static producer p = new producer(); //初始化一个新的生产者线程
	static consumer c = new consumer(); //初始化一个新的消费者线程
	static our_monitor mon = new our_monitor(); // 初始化一个新的管程
	
	public static void main(String args[]) {
		p.start(); //开始生产者线程
		c.start(); //开始消费者线程
	}
	
	static class producer extends Thread {
		@Override
		public void run() { //run方法包含了线程代码
			int item;
			while(true) { //生产者循环
				item = produce_item();
				mon.insert(item);
			}
		}
		
		private int produce_item() {
			int item = new Random().nextInt(1000);
			System.out.println("produce_item " + item);
			return item;
		}
	}
	
	static class consumer extends Thread {
		@Override
		public void run() { //run方法包含了线程代码
			int item;
			while (true) {
				item = mon.remove();
				consume_item(item);;
			}
		}
		
		public void consume_item(int val) {
			System.out.println("consume_item " + val);
		}
	}
	
	static class our_monitor { //这是一个管程
		private int buffer[] = new int[N];
		private int count = 0, lo = 0, hi = 0; //计数器和索引
		
		public synchronized void insert(int val) {
			if(count == N) go_to_sleep(); //如果缓冲区满，则进入休眠
			buffer[hi] = val; //向缓冲区中插入一个新的数据项
			hi = (hi + 1) % N; //设置下一个数据项的槽
			count = count + 1; //缓冲区中的数据项又多了一项
			if(count == 1) notify(); //如果消费者在休眠，则将其唤醒
		}
		
		public synchronized int remove() {
			int val;
			if (count ==0) go_to_sleep(); //如果缓冲区空，进入休眠
			val = buffer[lo]; //从缓冲区中取出一个数据项
			lo = (lo + 1) % N; //设置待取数据项的槽
			count = count - 1; //缓冲区中的数据项数目减少1
			if (count == N - 1) notify(); //如果生产者在睡眠，则将其唤醒 
			return val;
		}
		
		private void go_to_sleep () {
			try {
				wait();
			} catch(InterruptedException e) {
				
			}
		}
	}
	
}
