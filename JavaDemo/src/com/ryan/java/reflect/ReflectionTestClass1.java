package com.ryan.java.reflect;

public class ReflectionTestClass1 {

    private static final String FIELD_1 = "field_1_value";
    
    private String mField2 = "field_2_value";
    
    protected String mField3 = "field_3_value";
    
    public String mField4 = "field_4_value";
    
    private static String mField5 = "field_5_value";
    
    private static final int INT_FIELD_1 = 1;
    
    private int mIntField2 = 2;
    
    protected int mIntField3 = 3;
    
    public int mIntField4 = 4;
    
    private static int mIntField5 = 5;
    
    private static final boolean BOOLEAN_FIELD_1 = true;
    
    private boolean mBooleanField2 = false;
    
    protected boolean mBooleanField3 = true;
    
    public boolean mBooleanField4 = false;
    
    private static boolean mBooleanField5 = true;
    
    public void testMethod1(){
        System.out.println("testMethod1 ...");
    }
    
    public void testMethod2(String arg1){
        System.out.println("testMethod2 ... arg1 = "+arg1);
    }
    
    public static boolean testMethod3(){
        System.out.println("testMethod3 ...");
        return false;
    }
    
}
