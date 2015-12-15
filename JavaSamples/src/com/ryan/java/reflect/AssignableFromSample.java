package com.ryan.java.reflect;

/**
 *  ClassA.isAssignableFrom(ClassB) : 假如ClassA与参数ClassB表示的类或接口相同，或者是ClassB的父类，则返回true
 * 
 *  obj instanceof ClassA : 假如obj是ClassA的实例，或者是obj是ClassA的子类的实例，则返回true
 */
public class AssignableFromSample {
	
	public static class ClassA {
		
	}
	
	public static class ClassB extends ClassA{
		
	}

	public static void main(String[] args) {
		
		ClassA objA = new ClassA();
		ClassB objB = new ClassB();
		
		System.out.println("objA instanceof ClassB : " + (objA instanceof ClassB));
		System.out.println("objB instanceof ClassA : " + (objB instanceof ClassA));
		
		System.out.println("ClassA isAssignableFrom ClassB : " + ClassA.class.isAssignableFrom(ClassB.class));
		System.out.println("ClassB isAssignableFrom ClassA : " + ClassB.class.isAssignableFrom(ClassA.class));
		
	}
	
}
