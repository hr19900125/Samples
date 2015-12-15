package com.ryan.java.gson;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GsonTest {
    
    private static final Gson GSON = new Gson();
    
    private static class BagOfPrimitives {
        private int value1 = 1;
        private String value2 = "abc";
        private transient int value3 = 3;
    }
    
    public static void main(String[] args) {
        //
        System.out.println(GSON.toJson(1));
        System.out.println(GSON.toJson("abcdefg"));
        System.out.println(GSON.toJson(new Long(20)));
        int[] values = new int[]{1,2,3,4,5};
        System.out.println(GSON.toJson(values));
        
        //
        int one = GSON.fromJson("1", int.class);
        Integer one1 = GSON.fromJson("1", Integer.class);
        Long one2 = GSON.fromJson("1", Long.class);
        Boolean f = GSON.fromJson("false", Boolean.class);
        String str = GSON.fromJson("\"cbd\"", String.class);
        String[] anotherStr = GSON.fromJson("[\"lllllll\",\"aaaaa\"]", String[].class);
        for(String txt : anotherStr) {
            System.out.println(txt);
        }
        
        //
        BagOfPrimitives bag = new BagOfPrimitives();
        System.out.println(GSON.toJson(bag));
        String bagStr = "{\"value1\":1,\"value2\":\"abc\"}";
        BagOfPrimitives fromBag = GSON.fromJson(bagStr, BagOfPrimitives.class);
        
        //
        List<Integer> intList = new ArrayList<Integer>();
        for(int i = 0; i < 10 ; i ++){
            intList.add(i);
        }
        System.out.println(GSON.toJson(intList));
        
        //
//        Foo<Bar> foo = new Foo<Bar>();
//        foo.value = new Bar();
        //这种方式导致结果错误
//        System.out.println(GSON.toJson(foo));
        
        //解决方式 使用TypeToken
//        Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
//        System.out.println(GSON.toJson(foo, fooType));
        
//        String fooStr = "{\"value\":{\"a\":\"aaaaaxxx\",\"b\":\"bbbbbxxxx\"}}";
//        Foo<Bar> sFoo = GSON.fromJson(fooStr, foo.getClass());
//        System.out.println(sFoo.value);
        
        //DateTimeTypeConverter Test 
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
        Gson gson = gsonBuilder.create();
        DateTime time = new DateTime(new Date());
        String jsonStr = gson.toJson(time);
        System.out.println("time = "+gson.toJson(time));
        DateTime desTime = gson.fromJson(jsonStr, DateTime.class);
        System.out.println(desTime.toString());
        
        //PrettyPrinting
        Gson gson2 = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson2.toJson(bag));
        
    }
    
}
