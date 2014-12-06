package com.ryan.java.generic;

public class GenericTest {

    private static void testPoint(){
        Point<String> point = new Point<String>();
        point.setVar("this is Point");
        System.out.println("var length "+point.getVar().length());
    }
    
    private static void testNodePad(){
        NotePad<String, String> notePad = new NotePad<String, String>();
        notePad.setKey("this is Key");
        notePad.setValue("this is Value");
        System.out.println("Key = "+notePad.getKey()+", Value = "+notePad.getValue());
    }
    
    /**
     * 通配符测试
     */
    private static void testInfo1(){
        Info1<String> info = new Info1<String>();
        info.setVar("it");
        fun1(info);
    }
    
    private static void fun1(Info1<?> info){
        System.out.println("通配符方法:"+info.toString());
    }
    
    /**
     * 测试受限泛型
     */
    private static void testLimit() {
        Info<Integer> i1 = new Info<Integer>(); // 声明Integer的泛型对象
        Info<Float> i2 = new Info<Float>(); // 声明Float的泛型对象
        i1.setVar(30); // 设置整数，自动装箱
        i2.setVar(30.1f); // 设置小数，自动装箱
        fun(i1);
        fun(i2);
    }

    public static void fun(Info<? extends Number> temp) { // 只能接收Number及其Number的子类
        System.out.print(temp + "、");
    }
    
    /**
     * java 泛型无法向上转型
     */
    private static void testCannotCast(){
        Info1< String> i1 = new Info1< String>() ;  // 泛型类型为String  
        Info1< Object> i2 = null ;  
//        i2 = i1 ;        //这句会出错 incompatible types  
    }
    
    public static void main(String[] args){
        testPoint();
        testNodePad();
        testInfo1();
        testLimit();
    }
    
}
