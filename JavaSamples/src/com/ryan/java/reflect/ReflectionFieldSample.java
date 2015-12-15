package com.ryan.java.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class ReflectionFieldSample {
	
	public static class A {
		
		public String a = "a";
		
		protected String b = "b";
		
		private String c = "c";
		
		public List<Integer> d = null;
		
		public Map<String, Long> e = null;
		
	}
	
	public static void main(String[] args) {
		
		A a = new A();
		
		System.out.println("------------getFields-----------------");
		for(Field field : a.getClass().getFields()) {
			System.out.println("field " + field.getName() + ", Type " + field.getType() + ", GenericType " + field.getGenericType());
		}
		
		System.out.println("------------getGenericType-----------------");
		for(Field field : a.getClass().getDeclaredFields()) {
			System.out.println("field " + field.getName() + ", Type " + field.getType() + ", GenericType " + field.getGenericType());
		}
		
		
	}

}
