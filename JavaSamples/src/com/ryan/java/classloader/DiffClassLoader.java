package com.ryan.java.classloader;

import java.lang.reflect.Method;
/**
 * 测试是同样的一个类被两个不同的ClassLoader对象加载后，被判定为两个不同的Class实例
 * @author ryan
 *
 */
public class DiffClassLoader {

	public static void main(String[] args) {
		String classDataRootPath = "/home/ryan/HrProject/Samples/JavaSamples/src/";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(
				classDataRootPath);
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(
				classDataRootPath);
		String className = "com.ryan.java.classloader.Sample";
		try {
			Class<?> class1 = fscl1.findClass(className);
			Object obj1 = class1.newInstance();
			Class<?> class2 = fscl2.findClass(className);
			Object obj2 = class2.newInstance();
			Method setSampleMethod = class1.getMethod("setSample",
					java.lang.Object.class);
			setSampleMethod.invoke(obj1, obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
