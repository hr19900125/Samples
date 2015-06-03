package com.ryan.java.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExclusionStrategiesTest {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
        .setExclusionStrategies(new MyExclusionStrategy(String.class))
        .serializeNulls()
        .create();
        
        SampleObjectForTest src = new SampleObjectForTest();
        System.out.println(gson.toJson(src));
    }
    
}
