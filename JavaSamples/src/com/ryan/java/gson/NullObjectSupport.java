package com.ryan.java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class NullObjectSupport {

    static class Foo {
        private final String s;
        private final int i;
        
        public Foo(){
            this(null, 5);
        }
        
        public Foo(String s,int i){
            this.s = s;
            this.i = i;
        }
    }
    
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Foo foo = new Foo();
        System.out.println(gson.toJson(foo));
        
        System.out.println(gson.toJson(null));
    }
    
}
