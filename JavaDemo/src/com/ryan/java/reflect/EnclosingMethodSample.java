package com.ryan.java.reflect;

import java.lang.reflect.Method;

public class EnclosingMethodSample {

	public static class EnclosingMethod {
		public Class cls;
		
		public void invoke() {
			Object o = new Object() { 
	            public void m() {} 
	        };
	        
	        cls = o.getClass();
		}
		
	}
	
	public static void main(String[] args) {
		EnclosingMethod sample = new EnclosingMethod();
		sample.invoke();
		Method method = sample.cls.getEnclosingMethod();
		System.out.println("method : " + method);
	}
	
}
