package com.ryan.java.dp.singleton;

import java.util.HashMap;

public class HeritableSingleton {

    private static HashMap sinRegistry = new HashMap();
    private static HeritableSingleton singleton = new HeritableSingleton();
    
    protected HeritableSingleton() {
        
    }
    
    public static HeritableSingleton getInstance(String name){
        if(name == null) name = "HeritableSingleton";
        if(sinRegistry.get(name) == null) {
            try {
                sinRegistry.put(name, Class.forName(name).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
        return (HeritableSingleton) sinRegistry.get(name);
    }
    
    public void test() {
        System.out.println("get class success");
    }
    
}
