package com.ryan.java.dp.facade;
/**
 * 外观模式
 */
public class User {

    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startup();
        computer.shutdown();
    }  
    
}
