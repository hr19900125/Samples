package com.ryan.java.dp.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 这里其实也使用了模版方法模式 
 */
public abstract class AbstractSubject implements Subject{

    private Vector<Observer> vector = new Vector<Observer>(); 
    
    @Override
    public void add(Observer observer) {
        if(!vector.contains(observer)){
            vector.add(observer);
        }
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Enumeration<Observer> enumo = vector.elements();  
        while(enumo.hasMoreElements()){
            enumo.nextElement().update();  
        }
    }

}
