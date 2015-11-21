package com.ryan.java.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationRetentionTest {

	@RetentionAnnotation
	public void invokeMethod() {
		System.out.println("method: invokeMethod");
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		AnnotationRetentionTest test = new AnnotationRetentionTest();
		for(Method method : test.getClass().getMethods()) {
			if(method.isAnnotationPresent(RetentionAnnotation.class)) {
				method.invoke(test);
				break;
			}
		}
	}
	
}
