package com.ryan.java.reflect;

import java.lang.reflect.Constructor;

/**
 * 如果类是一个构造函数中的本地或匿名类，返回这个构造函数对象
 *
 */
public class EnclosingConstructorSample {

	public static class EnclosingConstructor
	{
	    public Class cls;
	    public EnclosingConstructor() {
	        Object o = new Object() { 
	            public void m() {} 
	        };
	        
	        cls = o.getClass();
	    }
	}
	
	public static void main(String[] args) throws Exception {
        EnclosingConstructor ec = new EnclosingConstructor();
        Constructor ctor = ec.cls.getEnclosingConstructor();
        System.out.println("ctor  : " + ctor);
    }
}
