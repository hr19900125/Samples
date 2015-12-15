package com.ryan.java.io;

public class MyException extends RuntimeException{

	private Throwable processException;

	public MyException(Throwable exception, Throwable exception2, String msg){
		super(msg, exception2);
		processException = exception;
	}
	
	public MyException(Throwable throwable, String msg) {
		super(msg,throwable);
	}
}
