package com.ryan.java.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {

	private static class Girl {
		
		public int age = 20;
		
		protected String birthday = "1995-01-01";
		
		private String name = "Who care";

		public Girl() {
			
		}
		
		public Girl(String name, String birthday, int age) {
			this.name = name;
			this.birthday = birthday;
			this.age = age;
		}
		
	}
	
	private static class Boy {
		
		public int age = 25;
		
		protected String birthday = "1990-01-25";
		
		private String name = "Huang";
		
		public Boy() {
			
		}
		
		public Boy(String name, String birthday, int age) {
			this.name = name;
			this.birthday = birthday;
			this.age = age;
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		
		/**
		 * 获取Class对象的方式
		 */
		// 1
		Class clazzGirl = Class.forName("com.ryan.java.reflect.Test$Girl");
		System.out.println("Method Class.forName() load " + clazzGirl.getName());
		
		// 2
		Class clazzBoy = Boy.class.getClass();
		System.out.println("Method class.getClass() load " + clazzBoy.getName());
		
		Boy boy0 = new Boy();
		Class clazzBoy0 = boy0.getClass();
		System.out.println("boy0 = " + clazzBoy0.getName());
		
		/**
		 * 由Class 来构造对象
		 */
		// 不带参数初始化
		Girl girl = (Girl) clazzGirl.newInstance();
		// 这个会失败 提示 Can not call newInstance() on the Class for java.lang.Class
//		Boy boy = (Boy) clazzBoy.newInstance();
		System.out.println("girl : name = " + girl.name + ", birthday = " + girl.birthday + ", age = " + girl.age);
//		System.out.println("boy : name = " + boy.name + ", birthday = " + boy.birthday + ", age = " + boy.age);
		
		// 使用Constructor 初始化
//		Girl girl2 = clazzGirl.newInstance();
		
		Class[] girlParamTypes = { String.class, String.class, int.class };
		Object[] girlParams = {"Rui", "2015-11-14", 1};
		Constructor con = clazzGirl.getConstructor(girlParamTypes); 
		Girl girl2 = (Girl) con.newInstance(girlParams);
		System.out.println("girl2 : name = " + girl2.name + ", birthday = " + girl2.birthday + ", age = " + girl2.age);
//		con.getParameterTypes()
		Class[] paramTypes = con.getParameterTypes();
		for(int i=0; i < paramTypes.length; i ++) {
			System.out.println("paramType = " + paramTypes[i].getName());
		}
		
		
		/**
		 * 获取Class 里面的Field的方式
		 */
		Field[] girlsFields = clazzGirl.getFields();
		if(girlsFields != null) {
			for(int i = 0; i < girlsFields.length; i ++) {
				System.out.println("FieldName = " + girlsFields[i].getName());
			}
		}
	}
	
}
