package com.ryan.java.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class MyExclusionStrategy implements ExclusionStrategy{

    private final Class<?> typeToSkip;
    
    public MyExclusionStrategy(Class<?> typeToSkip) {
        this.typeToSkip = typeToSkip;
    }
    
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Foo.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return (clazz == typeToSkip);
    }
    
}
