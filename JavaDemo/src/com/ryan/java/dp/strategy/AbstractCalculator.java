package com.ryan.java.dp.strategy;
/**
 * 辅助类 (有或没有),更简单的策略模式是直接实现接口实现不同逻辑，不需要继承
 */
public class AbstractCalculator {

    public int[] split(String exp,String opt){
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;
    }
    
}
