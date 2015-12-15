package com.ryan.java.annotation;

import java.lang.reflect.Method;

public class AnalysisAnnotation {
	/**
     * 在运行时分析处理annotation类型的信息
     * 
     * 
     */
    public static void main(String[] args) {
        try {
                //通过运行时反射API获得annotation信息
                Class<?> rt_class = Class.forName("com.ryan.java.annotation.Utility");
                Method[] methods = rt_class.getMethods();
                
                boolean flag = rt_class.isAnnotationPresent(Description.class);
                
                if(flag)
                {
                    Description description = (Description)rt_class.getAnnotation(Description.class);
                    System.out.println("Utility's Description--->"+description.value());
                    for (Method method : methods) {
                        if(method.isAnnotationPresent(Author.class))
                        {
                            Author author = (Author)method.getAnnotation(Author.class);
                            System.out.println("Utility's Author--->"+author.name()+" from "+author.group());
                            
                        }
                    }
                }
            
            
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

}
