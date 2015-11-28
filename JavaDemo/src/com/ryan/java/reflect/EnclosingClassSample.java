package com.ryan.java.reflect;

/**
 *  Class.getEnclosingClass() 实例
 *
 */
public class EnclosingClassSample {

	public EnclosingClassSample() {

		// class Outer as inner class for class ClassDemo
		class Outer {

			public void show() {
				// inner class of Class Outer
				class Inner {

					public void show() {
						System.out.print(getClass().getName() + " inner in...");
						System.out.println(getClass().getEnclosingClass());
					}
				}
				System.out.print(getClass().getName() + " inner in...");
				System.out.println(getClass().getEnclosingClass());

				// inner class show() function
				Inner i = new Inner();
				i.show();
			}
		}

		// outer class show() function
		Outer o = new Outer();
		o.show();
	}

	public static void main(String[] args) {
		EnclosingClassSample cls = new EnclosingClassSample();
	}

}
