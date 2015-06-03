package com.ryan.java.gson;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class MyListInstanceCreator implements InstanceCreator<MyList<?>>{

    @Override
    public MyList<?> createInstance(Type type) {
        return new MyList();
    }

}
