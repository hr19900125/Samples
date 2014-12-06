package com.ryan.java.generic;

public class GenericTest8 {
    
  //Java泛型的嵌套设置
    static class Info<T, V> { // 接收两个泛型类型
        private T var;
        private V value;

        public Info(T var, V value) {
            this.setVar(var);
            this.setValue(value);
        }

        public void setVar(T var) {
            this.var = var;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public T getVar() {
            return this.var;
        }

        public V getValue() {
            return this.value;
        }
    };

    static class Demo<S> {
        private S info;

        public Demo(S info) {
            this.setInfo(info);
        }

        public void setInfo(S info) {
            this.info = info;
        }

        public S getInfo() {
            return this.info;
        }
    };
    
    public static void main(String args[]) {
        Demo<Info<String, Integer>> d = null; // 将Info作为Demo的泛型类型
        Info<String, Integer> i = null; // Info指定两个泛型类型
        i = new Info<String, Integer>("汤姆", 30); // 实例化Info对象
        d = new Demo<Info<String, Integer>>(i); // 在Demo类中设置Info类的对象
        System.out.println("内容一：" + d.getInfo().getVar());
        System.out.println("内容二：" + d.getInfo().getValue());
    }
}
