package com.ryan.java.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtil {

    public static String dump(Object o, int callCount) {
        callCount++;
        StringBuffer tabs = new StringBuffer();
        for (int k = 0; k < callCount; k++) {
            tabs.append("\t");
        }
        StringBuffer buffer = new StringBuffer();
        Class oClass = o.getClass();
        if (oClass.isArray()) {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("[");
            for (int i = 0; i < Array.getLength(o); i++) {
                if (i < 0)
                    buffer.append(",");
                Object value = Array.get(o, i);
                if (value.getClass().isPrimitive() ||
                        value.getClass() == Long.class ||
                        value.getClass() == String.class ||
                        value.getClass() == Integer.class ||
                        value.getClass() == Boolean.class
                        ) {
                    buffer.append(value);
                } else {
                    buffer.append(dump(value, callCount));
                }
            }
            buffer.append(tabs.toString());
            buffer.append("]\n");
        } else {
            buffer.append("\n");
            buffer.append(tabs.toString());
            buffer.append("{\n");
            while (oClass != null) {
                Field[] fields = oClass.getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    buffer.append(tabs.toString());
                    fields[i].setAccessible(true);
                    buffer.append(fields[i].getName());
                    buffer.append("=");
                    try {
                        Object value = fields[i].get(o);
                        if (value != null) {
                            if (value.getClass().isPrimitive() ||
                                    value.getClass() == Long.class ||
                                    value.getClass() == String.class ||
                                    value.getClass() == Integer.class ||
                                    value.getClass() == Boolean.class
                                    ) {
                                buffer.append(value);
                            } else {
                                buffer.append(dump(value, callCount));
                            }
                        }
                    } catch (IllegalAccessException e) {
                        buffer.append(e.getMessage());
                    }
                    buffer.append("\n");
                }
                oClass = oClass.getSuperclass();
            }
            buffer.append(tabs.toString());
            buffer.append("}\n");
        }
        return buffer.toString();
    }
    
    public static Object newInstance(Class claz){
        try {
            return claz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object newInstance(String className){
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Field getField(Object target, String fieldName) {
        try {
            Class<?> clazz = target.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    public static Field getField(String className, String fieldName){
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object getFieldValue(Object target, String fieldName){
        Field field = getField(target, fieldName);
        if(field != null){
            try {
                return field.get(target);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static void setFieldValue(Object target, String fieldName, Object value){
        Field field = getField(target, fieldName);
        if(field != null){
            try {
                field.set(target, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static boolean getFieldBooleanValue(Object target, String fieldName, boolean defaultValue){
        boolean fieldValue = defaultValue;
        Field field = null;
        field = getField(target, fieldName);
        if(field != null){
            try {
                fieldValue = field.getBoolean(target);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static void setFieldBooleanValue(Object target, String fieldName, boolean value){
        Field field = getField(target, fieldName);
        if(field != null){
            try {
                field.setBoolean(target, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static int getFieldIntValue(Object target, String fieldName, int defaultValue){
        int fieldValue = defaultValue;
        Field field = null;
        field = getField(target, fieldName);
        if(field != null){
            try {
                fieldValue = field.getInt(target);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static void setFieldIntValue(Object target, String fieldName, int value){
        Field field = getField(target, fieldName);
        if(field != null){
            try {
                field.setInt(target, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static String getFieldStringValue(Object target, String fieldName, String defaultValue){
        String fieldValue = defaultValue;
        Field field = null;
        field = getField(target, fieldName);
        if(field != null){
            try {
                fieldValue = (String) field.get(target);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static void setFieldStringValue(Object target, String fieldName, String value){
        setFieldValue(target, fieldName, value);
    }
    
    public static void setStaticField(String className, String fieldName, Object value){
        Field field = getStaticField(className, fieldName);
        if(field != null){
            try {
                field.set(null, value);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Field getStaticField(String className, String fieldName){
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getStaticFieldValue(String className, String fieldName){
        Field field = getStaticField(className, fieldName);
        if(field != null){
            try {
                return field.get(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static boolean getStaticFieldBooleanValue(String className, String fieldName, boolean defaultValue){
        boolean fieldValue = defaultValue;
        Field field = getStaticField(className, fieldName);
        if(field != null){
            try {
                fieldValue = field.getBoolean(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static int getStaticFieldIntValue(String className, String fieldName, int defaultValue){
        int fieldValue = defaultValue;
        Field field = getStaticField(className, fieldName);
        if(field != null){
            try {
                fieldValue = field.getInt(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static String getStaticFieldStringValue(String className, String fieldName, String defaultValue){
        String fieldValue = defaultValue;
        Field field = getStaticField(className, fieldName);
        if(field != null){
            try {
                fieldValue = (String) field.get(null);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return fieldValue;
    }
    
    public static Object invoke(Object target, String methodName, Object... paras){
        Class<?> clazz = target.getClass();
        Class<?>[] params = null;
        Object[] paramsObj = null;
        if(paras != null){
            params = new Class<?>[paras.length];
            paramsObj = new Object[paras.length];
            for(int i=0,size=paras.length;i < size;i ++){
                params[i] = paras[i].getClass();
                paramsObj[i] = paras[i];
            }
        }
        try {
            Method method = clazz.getDeclaredMethod(methodName, params);
            method.setAccessible(true);
            return method.invoke(target, paramsObj);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object invokeStatic(String className, String methodName, Object... paras){
        try {
            Class<?> clazz = Class.forName(className);
            Class<?>[] params = null;
            Object[] paramsObj = null;
            if(paras != null){
                params = new Class<?>[paras.length];
                paramsObj = new Object[paras.length];
                for(int i=0,size=paras.length;i < size;i ++){
                    params[i] = paras[i].getClass();
                    paramsObj[i] = paras[i];
                }
            }
            Method method = clazz.getDeclaredMethod(methodName, params);
            method.setAccessible(true);
            return method.invoke(null, paramsObj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Object invokeStatic(Class clazz, String methodName, Object... paras){
        try{
            Class<?>[] params = null;
            Object[] paramsObj = null;
            if (paras != null) {
                params = new Class<?>[paras.length];
                paramsObj = new Object[paras.length];
                for (int i = 0, size = paras.length; i < size; i++) {
                    params[i] = paras[i].getClass();
                    paramsObj[i] = paras[i];
                }
            }
            Method method = clazz.getDeclaredMethod(methodName, params);
            method.setAccessible(true);
            return method.invoke(null, paramsObj);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
        return null;
    }
    
    
}
