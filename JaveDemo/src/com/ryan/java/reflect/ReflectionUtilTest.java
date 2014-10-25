package com.ryan.java.reflect;

public class ReflectionUtilTest {

     public static void testGetName(){
         System.out.println("testGetName---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         System.out.println("obj : name  "+obj.getClass().getName());
         System.out.println("Class : name  "+ReflectionTestClass1.class.getName());
     }
    
     public static void testDump(){
         System.out.println("testDump---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         String result = ReflectionUtil.dump(obj, 1);
         System.out.println(result);
     }
     
     public static void testGetFieldStringValue(){
         System.out.println("testGetFieldStringValue---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         String field1Value = ReflectionUtil.getFieldStringValue(obj, "FIELD_1", "");
         String field2Value = ReflectionUtil.getFieldStringValue(obj, "mField2", "");
         String field3Value = ReflectionUtil.getFieldStringValue(obj, "mField3", "");
         String field4Value = ReflectionUtil.getFieldStringValue(obj, "mField4", "");
         String field5Value = ReflectionUtil.getFieldStringValue(obj, "mField5", "");
         System.out.println("FIELD_1 = "+field1Value+" \n");
         System.out.println("mField2 = "+field2Value+" \n");
         System.out.println("mField3 = "+field3Value+" \n");
         System.out.println("mField4 = "+field4Value+" \n");
         System.out.println("mField5 = "+field5Value+" \n");
     }
     
     public static void testGetFieldBooleanValue(){
         System.out.println("testGetFieldBooleanValue---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         boolean field1Value = ReflectionUtil.getFieldBooleanValue(obj, "BOOLEAN_FIELD_1", false);
         boolean field2Value = ReflectionUtil.getFieldBooleanValue(obj, "mBooleanField2", false);
         boolean field3Value = ReflectionUtil.getFieldBooleanValue(obj, "mBooleanField3", false);
         boolean field4Value = ReflectionUtil.getFieldBooleanValue(obj, "mBooleanField4", false);
         boolean field5Value = ReflectionUtil.getFieldBooleanValue(obj, "mBooleanField5", false);
         System.out.println("FIELD_1 = "+field1Value+" \n");
         System.out.println("mField2 = "+field2Value+" \n");
         System.out.println("mField3 = "+field3Value+" \n");
         System.out.println("mField4 = "+field4Value+" \n");
         System.out.println("mField5 = "+field5Value+" \n");
     }
     
     public static void testGetFieldIntValue(){
         System.out.println("testGetFieldIntValue---------------------------");
         ReflectionTestClass1 obj = new ReflectionTestClass1();
         int field1Value = ReflectionUtil.getFieldIntValue(obj, "INT_FIELD_1", -1);
         int field2Value = ReflectionUtil.getFieldIntValue(obj, "mIntField2", -1);
         int field3Value = ReflectionUtil.getFieldIntValue(obj, "mIntField3", -1);
         int field4Value = ReflectionUtil.getFieldIntValue(obj, "mIntField4", -1);
         int field5Value = ReflectionUtil.getFieldIntValue(obj, "mIntField5", -1);
         System.out.println("FIELD_1 = "+field1Value+" \n");
         System.out.println("mField2 = "+field2Value+" \n");
         System.out.println("mField3 = "+field3Value+" \n");
         System.out.println("mField4 = "+field4Value+" \n");
         System.out.println("mField5 = "+field5Value+" \n");
     }
     
     public static void main(String[] args){
         testGetName();
         testDump();
         testGetFieldStringValue();
         testGetFieldBooleanValue();
         testGetFieldIntValue();
     }
    
}
