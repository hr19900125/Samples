package com.ryan.java.annotation;

import com.ryan.java.annotation.test.A;

@TypeAnnotation
public class AnnotationTargetTest {

	@FieldAnnotation
	private String sb = "I am Sb";
	
	@ConstructorAnnotation
	public AnnotationTargetTest() {
		
	}
	
	@MethodAnnotation
	public void test(@ParameterAnnotation()String str) {
		System.out.println("invoke Method Test " + str);
	}
	
	@TypeAnnotation
	enum NUMBER {
		ONE,
		TWO,
		THREE
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		A a = new A();
		Class.forName("com.ryan.java.annotation.test.A");
		//使用PackageAnnotation
		Package p = Package.getPackage("com.ryan.java.annotation.test");
		if(p == null) {
			System.out.println("p == null");
		}
		System.out.println("isAnnotationPresent = " + p.isAnnotationPresent(PackageAnnotation.class));
		if(p != null && p.isAnnotationPresent(PackageAnnotation.class)) {
			PackageAnnotation pa = p.getAnnotation(PackageAnnotation.class);
			if(pa != null) {
				System.out.println("value = "+pa.value());
			}
		} 
	}
	
}
