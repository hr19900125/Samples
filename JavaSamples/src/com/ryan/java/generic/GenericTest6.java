
package com.ryan.java.generic;
//使用泛型统一传入的参数类型
public class GenericTest6 {
    
    static class Info<T> { // 指定上限，只能是数字类型
        private T var; // 此类型由外部决定

        public T getVar() {
            return this.var;
        }

        public void setVar(T var) {
            this.var = var;
        }

        public String toString() { // 覆写Object类中的toString()方法
            return this.var.toString();
        }
    };
    
    public static void main(String args[]) {
        Info<String> i1 = new Info<String>();
        Info<String> i2 = new Info<String>();
        i1.setVar("HELLO"); // 设置内容
        i2.setVar("汤姆"); // 设置内容
        add(i1, i2);
    }

    public static <T> void add(Info<T> i1, Info<T> i2) {
        System.out.println(i1.getVar() + " " + i2.getVar());
    }
};
