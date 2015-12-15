package com.ryan.java.dp.bridge;

public class MyBridge extends Bridge{

    @Override
    public void method() {
        getSource().method();  
    }

}
