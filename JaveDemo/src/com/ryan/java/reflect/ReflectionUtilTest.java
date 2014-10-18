package com.ryan.java.reflect;

public class ReflectionUtilTest {

     public static void testGetName(){
         System.out.println("testGetName---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         System.out.println("obj : name  "+obj.getClass().getName());
         System.out.println("Class : name  "+ReflectionTestClass1.class.getName());
     }
    
     public static void testDump(){
         System.out.println("testDump---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         String result = ReflectionUtil.dump(obj, 1);
         System.out.println(result);
     }
     
     public static void testGetFieldValue(){
         System.out.println("testGetFieldValue---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         String fieldValue = ReflectionUtil
     }
     
     public static void main(String[] args){
         testGetName();
         testDump();
     }
    
}
