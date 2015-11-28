package com.ryan.java.reflect;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ryan.java.dp.adapter.ClassAdapterTest;

public class Reflection {
	
	public static abstract class Human {
		
	    public void sleep() {
			System.out.println("Sleep");
		}
		
	}
	
	public interface Programer {
		
		public void coding();
		
	}
	
	public interface Worker {
		
		public void work();
		
	}
	
	public enum LANGUAGE {
		JAVA,
		C
	}

	public static class Huangrui extends Human implements Programer, Worker{
		
		public static String sex = "男";
		public String name = "huangrui";
		protected int age = 18;
		private String enjoy = "coding";
		Date birthday = new Date();
		public LANGUAGE language = LANGUAGE.JAVA;
		public final LANGUAGE MYLANGUAGE = LANGUAGE.JAVA;
		
		public Huangrui() {
			System.out.println("Constructor invoke");
		}
		
		public Huangrui(String name, int age, String enjoy) {
			this.name = name;
			this.age = age;
			this.enjoy = enjoy;
		}
		
		@Override
		public void coding() {
            System.out.println("I can coding");			
		}

		@Override
		public void work() {
			System.out.println("workkkkkkkkkk");
		}
		
		private void say() {
			System.out.println("sayyyyyyyyyyy");
		}
		
		public static void staticMethod() {
			System.out.println("invoke static Method");
		}
		
		public static void staticMethodWithArgs(int a, String b) {
			System.out.println("Args a = " + a + ", b = " + b);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		/**
		 * 使用Class.forName 方法获取Class对象
		 */
		Class<Huangrui> clazz = (Class<Huangrui>) Class.forName("com.ryan.java.reflect.Reflection$Huangrui");
		
		System.out.println("clazz.getName() = " + clazz.getName());
		System.out.println("clazz.getSimpleName() = " + clazz.getSimpleName());
		//获取Class的父类
		Class cls = clazz;
		String str = cls.getName();
		do {
			Class parent = cls.getSuperclass();
			if(parent == null) {
				break;
			}
			str += " ---> " + parent.getName();
			cls = parent;
		}while(cls != null);
		System.out.println(str);
        System.out.println("ClassLoader = " + clazz.getClassLoader());
		//获取Class实现的interface
        Class[] interfaces = clazz.getInterfaces();
        if(interfaces != null) {
        	for(Class c : interfaces) {
        		System.out.println(clazz.getSimpleName() + " implements " + c.getName() + " c.isInterface() = " + c.isInterface() + "  c.isEnum() = " + c.isEnum());
        	}
        }
        
        //enum 类型
        Class class1 = Class.forName("com.ryan.java.reflect.Reflection$LANGUAGE");
        System.out.println("Name = " + class1.getName() + " class1.isEnum() = " + class1.isEnum());
        //class1.asSubclass 说明 public <U> Class<? extends U> asSubclass(Class<U> clazz) 
        List<String> strList = new ArrayList<String>();  
        Class<? extends List> strList_cast = strList.getClass().asSubclass(List.class);
        
        Class<? extends Human> humanClazz = clazz.asSubclass(Human.class);
        Human human = humanClazz.newInstance();
        System.out.println(humanClazz.getName() + ", human = " + human);
        human.sleep();
        
        //http://blog.csdn.net/feier7501/article/details/19088361
        System.out.println("clazz.isSynthetic() = " + clazz.isSynthetic());
        
        clazz.isAssignableFrom(cls)
        
        System.out.println("-----------------------------------------------------------------------");
		/**
		 * 使用Constructor来实例化对象
		 */
		//不带参数的构造函数
		Constructor constructor = clazz.getConstructor();
		Huangrui h = (Huangrui) constructor.newInstance();
		System.out.println("name = " + h.name + ", age = "+h.age + ", enjoy = " + h.enjoy);
		
		//带参数的构造函数
		Class[] paramTypes = new Class[]{String.class, int.class, String.class};
		Constructor constructor2 = clazz.getConstructor(paramTypes);
		Object[] params = new Object[]{"H", 21, "eat"};
		h = (Huangrui) constructor2.newInstance(params);
		System.out.println("name = " + h.name + ", age = "+h.age + ", enjoy = " + h.enjoy);
		
		System.out.println("-----------------------------------------------------------------------");
		
		/**
		 * 获取Field的方式
		 * 
		 * 	           类内部  本包	子类  外部包 
         *    public 	 √	    √	 √	   √
         *    protected	 √	    √	 √	   ×
         *    default 	 √	    √	 ×	   ×
         *    private	 √	    ×	 ×	   ×
		 * 
		 * 
		 * Modifiers 对应值总结
		 * 没有修饰符 -> 0
		 * public -> 1
		 * private -> 2
		 * protected -> 4
		 * static -> 8
		 * final -> 16
		 * synchronized -> 32
		 * volatile -> 64
		 * transient -> 128
		 * native -> 256
		 * interface -> 512
		 * abstract -> 1024
		 * strict -> 2048
		 * 
		 * 
		 */
		//获取public Field
		Field nameField = h.getClass().getField("name");
		System.out.println("Field Modifiers[" + nameField.getModifiers()+"] -- GenericType[" + nameField.getGenericType()+"] -- Value[" + nameField.get(h)+"]");
		int modifiers = nameField.getModifiers();
		System.out.println("Name Field modifiers = "+modifiers+" Modifier.toString = " + Modifier.toString(modifiers));
		
		//获取protected Field
		Field ageField = h.getClass().getDeclaredField("age");
		System.out.println("Field Modifiers[" + ageField.getModifiers()+"] -- GenericType[" + ageField.getGenericType()+"] -- Value[" + ageField.getInt(h)+"]");
		modifiers = ageField.getModifiers();
		System.out.println("Age Field modifiers = "+modifiers+" Modifier.toString = " + Modifier.toString(modifiers));
		
		//获取private Field
		Field enjoyField = h.getClass().getDeclaredField("enjoy");
		//假如访问private的字段或方法，需要setAccessible(true)
		enjoyField.setAccessible(true);
		System.out.println("Field Modifiers[" + enjoyField.getModifiers()+"] -- GenericType[" + enjoyField.getGenericType()+"] -- Value[" + enjoyField.get(h)+"]");
		modifiers = enjoyField.getModifiers();
		System.out.println("Age Field modifiers = "+modifiers+" Modifier.toString = " + Modifier.toString(modifiers));
		
		//获取default Field
		Field birthdayField = h.getClass().getDeclaredField("birthday");
		System.out.println("Field Modifiers[" + birthdayField.getModifiers()+"] -- GenericType[" + birthdayField.getGenericType()+"] -- Value[" + birthdayField.get(h)+"]");
	    modifiers = birthdayField.getModifiers();
	    System.out.println("Age Field modifiers = "+modifiers+" Modifier.toString = " + Modifier.toString(modifiers));
	    
	    //获取static Field
	    Field sexField = h.getClass().getField("sex");
	    System.out.println("Field Modifiers[" + sexField.getModifiers()+"] -- GenericType[" + sexField.getGenericType()+"] -- Value[" + sexField.get(null)+"]");
	    modifiers = sexField.getModifiers();
	    System.out.println("Age Field modifiers = "+modifiers+" Modifier.toString = " + Modifier.toString(modifiers));
	    
	    //Field Api 测试
	    
	    System.out.println("birthdayField.getType().getName() = " + birthdayField.getType().getName());
	    System.out.println("birthdayField.getType().getCanonicalName() = " + birthdayField.getType().getCanonicalName());
	    Field privateField = h.getClass().getDeclaredField("enjoy");
	    System.out.println("privateField privateField.isAccessible() = " + privateField.isAccessible());
	    //下面的例子发现enumField.isEnumConstant() 并不是指enum变量
	    Field enumField = h.getClass().getDeclaredField("language");
	    System.out.println("enumField enumField.isEnumConstant() = " + enumField.isEnumConstant() + " enumField.getType() = " + enumField.getType());
        Field field2 = h.getClass().getDeclaredField("MYLANGUAGE");
        System.out.println("field2 enumField.isEnumConstant() = " + field2.isEnumConstant() + " field2.getType() = " + field2.getType());
	    
        System.out.println("LANGUAGE.JAVA.getClass().getName() = " + LANGUAGE.JAVA.getClass().getName() + ", " + LANGUAGE.JAVA.getClass().isEnum());
        
        
        
	    System.out.println("-----------------------------------------------------------------------");
//	    birthdayField.getType().getComponentType()
	    /**
	     * 获取Method的方式
	     */
	    //获取对象的Method
	    Method method = h.getClass().getDeclaredMethod("say");
	    method.setAccessible(true);
	    method.invoke(h);
	    
	    //获取static method
	    method = clazz.getDeclaredMethod("staticMethod");
	    method.setAccessible(true);
	    method.invoke(null);
	    
	    //获取带参数static method
	    method = clazz.getDeclaredMethod("staticMethodWithArgs", new Class[]{int.class, String.class});
	    method.setAccessible(true);
	    method.invoke(null, new Object[]{19, "abcdefg"});
	    
//	    System.out.println("method name = " + method.getName() + ", " + method.get);
	    
	}
	
	
	
}
