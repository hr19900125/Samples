package com.ryan.java.dp.factory;

public class SmsSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is SmsSender!");
	}

}
