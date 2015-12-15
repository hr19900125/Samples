package com.ryan.java.dp.observer;

public class Observer1 implements Observer{

    @Override
    public void update() {
        System.out.println("Observer1 update "+System.currentTimeMillis());    
    }

}
