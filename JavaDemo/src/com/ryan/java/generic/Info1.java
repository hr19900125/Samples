package com.ryan.java.generic;

public class Info1<T> {

    private T var;

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return this.var;
    }

    public String toString() { // 直接打印
        return this.var.toString();
    }
    
}
