package com.ryan.java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Modifier;

public class ExcludingField {

    static class ModifierObject {
        private static String a = "static field a";
        private String b = "normal field b";
        transient String c = "transient";
        volatile int d = 4;
        private String e = "field e";
        @Expose
        private String f = "field f";
    }
    
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE).excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(gson.toJson(new ModifierObject()));
    }
    
}
