package com.ryan.java.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * con.getExceptionTypes() 实例
 *
 */
public class GetExceptionTypesSample {
	
	public static class A {
		
		int a = -1;
		
		public A() throws Exception, IllegalAccessError {
			if(a <= 0) {
//				throw new NullPointerException();
			} else if(a > 0 && a < 10) {
				throw new IllegalAccessError();
			} else {
				throw new Exception();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		A x = new A();
		Constructor con = x.getClass().getConstructor();
		Class[] classes = con.getExceptionTypes();
		if(classes != null) {
			for(Class clz : classes) {
				System.out.println(clz.toString());
			}
		}
		
		Type[] types = con.getGenericExceptionTypes();
		if(types != null) {
			for(Type type : types) {
				System.out.println(type.toString());
			}
		}
	}
	
}
