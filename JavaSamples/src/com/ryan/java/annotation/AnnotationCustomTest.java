package com.ryan.java.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AnnotationCustomTest {
	
	public interface Student {
		public void speakEnglish();
	}
	
	public static class Lilei implements Student{
		
		@TeacherCall("lilei")
		public void speakEnglish() {
			System.out.println("My name is lilei");
		}
		
	}
	
	public static class Hanmeimei implements Student{
		
		@TeacherCall("hanmeimei")
		public void speakEnglish() {
			System.out.println("My name is hanmeimei");
		}
		
	}
	
	public static class Huangrui implements Student{
		
		@TeacherCall("huangrui")
		public void speakEnglish() {
			System.out.println("My name is Huangrui");
		}
		
	}
	
	public static class Teacher {
		private Map<String, Student> students = new HashMap<String, Student>();
		private Map<String, Method> map = new HashMap<String, Method>();
		
		public Teacher () {
			Lilei lilei = new Lilei();
			Hanmeimei hanmeimei = new Hanmeimei();
			Huangrui huangrui = new Huangrui();
			
			try {
				Method m1 = lilei.getClass().getMethod("speakEnglish");
				if(m1.isAnnotationPresent(TeacherCall.class)) {
					TeacherCall a1 = m1.getAnnotation(TeacherCall.class);
					String name = a1.value();
					students.put(name,lilei);
					map.put(name, m1);
				}
				
				Method m2 = hanmeimei.getClass().getMethod("speakEnglish");
				if(m2.isAnnotationPresent(TeacherCall.class)) {
					TeacherCall a2 = m2.getAnnotation(TeacherCall.class);
					String name = a2.value();
					students.put(name, hanmeimei);
					map.put(name, m2);
				}
				
				Method m3 = huangrui.getClass().getMethod("speakEnglish");
				if(m3.isAnnotationPresent(TeacherCall.class)) {
					TeacherCall a3 = m3.getAnnotation(TeacherCall.class);
					String name = a3.value();
					students.put(name, huangrui);
					map.put(name, m3);
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void ask(String name) {
			if(map.containsKey(name)) {
				Student student = students.get(name);
				Method method = map.get(name);
				try {
					method.invoke(student);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Teacher teacher = new Teacher();
		teacher.ask("huangrui");
	}

}
