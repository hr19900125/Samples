
package com.ryan.java.generic;

interface Info3<T> { // 在接口上定义泛型
    public T getVar(); // 定义抽象方法，抽象方法的返回值就是泛型类型
}

class InfoImpl2 implements Info3<String> { // 定义泛型接口的子类
    private String var; // 定义属性

    public InfoImpl2(String var) { // 通过构造方法设置属性内容
        this.setVar(var);
    }

    public void setVar(String var) {
        this.var = var;
    }

    public String getVar() {
        return this.var;
    }
};

public class GenericTest3 {
    public static void main(String arsg[]) {
        Info3 i = null; // 声明接口对象
        i = new InfoImpl2("汤姆"); // 通过子类实例化对象
        System.out.println("内容：" + i.getVar());
    }
};
