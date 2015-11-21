package com.ryan.java.annotation;

import java.lang.reflect.Method;

public class AnnotationInheritedTest {

	@InheritedAnnotation
	public static abstract class Father {
		
		@InheritedAnnotation
		public void work() {
			System.out.println("workkkkkkkkkk");
		}
		
		@InheritedAnnotation
		public void abstractWork() {
			System.out.println("father abstractWork");
		}
		
	}
	
	public static class Son extends Father {
		
		public void learn() {
			System.out.println("learnnnnnnnnn");
		}
		
		@Override
		public void work() {
			System.out.println("son workkkkkkk");
		}
	}
	
	public static void main(String[] args) {
		Son son = new Son();
		
		for(Method method : son.getClass().getMethods()) {
			System.out.println(method.getName());
			if(method.isAnnotationPresent(InheritedAnnotation.class)) {
				System.out.println("method " + method.getName() + " InheritedAnnotation ");
			}
		}
		
		if(son.getClass().isAnnotationPresent(InheritedAnnotation.class)) {
			System.out.println("Father 的类的InheritedAnnotation注解被Son继承了");
		} else {
			System.out.println("注释掉@Inherited 之后 ，Father 的类的InheritedAnnotation注解不能被Son继承了");
		}
	}
	
}
