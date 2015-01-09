package com.ryan.java.dp.template;
/**
 * 
 * 模板方法模式，这个经常用到，经常是抽象类里面实现通用逻辑，不同的逻辑流程抽象方法给子类使用
 * 
 */
public abstract class AbstractCalculator {

    /*主方法，实现对本类其它方法的调用*/  
    public final int calculate(String exp,String opt){  
        int array[] = split(exp,opt);  
        return calculate(array[0],array[1]);  
    }  
      
    /*被子类重写的方法*/  
    abstract public int calculate(int num1,int num2);  
      
    private int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    }  
    
}
