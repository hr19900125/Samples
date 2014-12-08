package com.ryan.java.dp.factory2;

public class SmsSender implements Sender{

	@Override
	public void send() {
       System.out.println("This is sms Sender!");		
	}

}
