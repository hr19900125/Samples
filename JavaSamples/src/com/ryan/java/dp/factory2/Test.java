package com.ryan.java.dp.factory2;
/**
 * 抽象工厂模式
 *
 */
public class Test {

	public static void main(String[] args){
		Provider provider = new SendMailFactory();
		Sender sender = provider.produce();
		sender.send();
	}
	
}
