package com.ryan.java.dp.singleton;

public class HeritableSingletonChild extends HeritableSingleton{

    public HeritableSingletonChild() {
        
    }
    
    public static HeritableSingletonChild getInstance() {
        return (HeritableSingletonChild) HeritableSingleton.getInstance("HeritableSingletonChild");
    }
    
    @Override
    public void test() {
        System.out.println("get class success 111");
    }
    
}
