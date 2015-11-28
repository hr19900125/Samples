package com.ryan.java.reflect;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试Class.getName 、getSimpleName 和 getCanonicalName 的区别 
 *
 */
public class GetNameSample {
	
	public interface Fruit {
		
	}
	
    public static class Apple implements Fruit {
    	
    }
	
	public static void main(String[] args) {
		
		Fruit apple = new Apple();
		System.out.println(apple.getClass().getCanonicalName());
		System.out.println(apple.getClass().getSimpleName());
		System.out.println(apple.getClass().getName());
		
		Apple[] arrApple = new Apple[]{};
        System.out.println(arrApple.getClass().getCanonicalName());
        System.out.println(arrApple.getClass().getSimpleName());
        System.out.println(arrApple.getClass().getName());
        
        System.out.println(String.class.getCanonicalName());
        System.out.println(String.class.getSimpleName());
        System.out.println(String.class.getName());
        
        System.out.println(int.class.getCanonicalName());
        System.out.println(int.class.getSimpleName());
        System.out.println(int.class.getName());
        
        Apple a1 = new Apple();
        Apple a2 = new Apple();
        List<Apple> appleList = new ArrayList<GetNameSample.Apple>();
        appleList.add(a1);
        appleList.add(a2);
        System.out.println(appleList.getClass().getCanonicalName());
        System.out.println(appleList.getClass().getSimpleName());
        System.out.println(appleList.getClass().getName());
		
	}

}
