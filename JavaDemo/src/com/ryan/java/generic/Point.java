package com.ryan.java.generic;
/**
 * 普通泛型 
 */
public class Point<T> {

    private T var; //var的类型由T指定，即：由外部指定
    
    public T getVar(){
        return var;
    }
    
    public void setVar(T var){
        this.var = var;
    }
}
