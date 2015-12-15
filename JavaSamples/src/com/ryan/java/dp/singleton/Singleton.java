package com.ryan.java.dp.singleton;
/**
 * 单例模式
 * 
 */
public class Singleton {

	private static Singleton instance = null;
	
	private Singleton(){
		
	}
	
	private static class SingletonFactory{
		private static Singleton instance = new Singleton();
	}
	
	public static Singleton getInstance(){
		return SingletonFactory.instance;
	}
	
	public Object readResolve(){
		return instance;
	}
	
}
