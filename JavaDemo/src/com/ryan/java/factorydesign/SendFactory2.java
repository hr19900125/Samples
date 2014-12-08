package com.ryan.java.factorydesign;
/**
 * 多个工厂方法模式 
 *
 */
public class SendFactory2 {

	public Sender produceMail(){
		return new MailSender();
	}
	
	public Sender produceSms(){
		return new SmsSender();
	}
	
}
