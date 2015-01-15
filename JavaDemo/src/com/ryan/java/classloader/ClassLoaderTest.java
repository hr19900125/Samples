package com.ryan.java.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;


public class ClassLoaderTest {


	public static void main(String[] args) {
        //打印bootstraploader加载的路径列表
		URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
		for (int i = 0; i < urls.length; i++) {
		   System.out.println(urls[i].toExternalForm());
		}
		
		//使用加载ClassLoaderTest类的ClassLoader来加载ClassTest
		try {
			Class clz = ClassLoader.getSystemClassLoader().loadClass("com.ryan.java.classloader.ClassTest");
		    Method method = clz.getMethod("method");
		    method.invoke(clz.newInstance());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		
		
	}

}
