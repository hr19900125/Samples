package com.ryan.java.factorydesign;

public class FactoryTest {
	
	public static void main(String[] args){
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("sms");
		sender.send();
	}

}
