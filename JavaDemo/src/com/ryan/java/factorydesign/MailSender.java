package com.ryan.java.factorydesign;

public class MailSender implements Sender{

	@Override
	public void send() {
        System.out.println("this is mailSender!");		
	}

}
