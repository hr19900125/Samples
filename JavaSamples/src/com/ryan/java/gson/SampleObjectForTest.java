package com.ryan.java.gson;

public class SampleObjectForTest {

    @Foo
    private final int annotateField;
    private final String stringField;
    private final long longField;
//    private final Class<?> clazzField;
    
    public SampleObjectForTest(){
        annotateField = 5;
        stringField = "someDefaultValue";
        longField = 1234;
//        clazzField = clazz;
    }
    
}
