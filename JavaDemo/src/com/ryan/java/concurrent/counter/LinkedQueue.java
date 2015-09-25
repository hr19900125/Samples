package com.ryan.java.concurrent.counter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Michael-Scott 非阻塞队列算法中的插入 
 *
 */
public class LinkedQueue <E> {

	private static class Node<E> {
		final E item;
		final AtomicReference<Node<E>> next;
		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = new AtomicReference<LinkedQueue.Node<E>>(next);
		}
	}
	
	private AtomicReference<Node<E>> head = new AtomicReference<LinkedQueue.Node<E>>(new Node<E>(null, null));
	private AtomicReference<Node<E>> tail = head;
	
	public boolean put(E item) {
		Node<E> newNode = new Node<E>(item, null);
		while (true) {
			Node<E> curTail = tail.get();
			Node<E> residue = curTail.next.get();
			if (curTail == tail.get()) {
				if(residue == null) {
					if(curTail.next.compareAndSet(null, newNode)) {
						tail.compareAndSet(curTail, newNode);
						return true;
					}
				} else {
					tail.compareAndSet(curTail, residue);
				}
			}
		}
	}
}
