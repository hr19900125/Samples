package com.ryan.java.reflect;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * getComponentType 返回的是数组[]里面的类型，假如是其他的类型的话，该方法返回null
 */
public class ReflectionMethodSample {
	
	public static class A {
		
		public static Integer staticInvoke(int a, String b) {
			System.out.println("a = " + a + " , " + b);
			return new Integer(10);
		}
		
		public void invoke(List<Long> list, Map<String, Boolean> map, AtomicBoolean flag, int[] array) {
			
		}
		
	}
	
	public static void main(String[] args) {
		
		A a = new A();
		
		System.out.println("--------------------getMethods-------------------");
		for(Method method : a.getClass().getMethods()) {
			System.out.println("method name " + method.getName() + ", ReturnType " + method.getReturnType());
			for(Class cls : method.getParameterTypes()) {
				System.out.println("Param " + cls.getName() + ", ComponentType " + cls.getComponentType());
			}
			System.out.println("----------------------------------------------");
		}
		
	}

}
