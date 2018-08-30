package com.hr.train.reflect.test;

import com.hr.train.reflect.Reflect;

import javax.management.relation.Relation;

public class ReflectTest {

    private static class Person {
        private int age = 18;

        private String name = "Huang";

        private void sayHello() {
            System.out.println("hello !");
        }
    }

    public static void main(String[] args) {
        Person person = new Person();
        Reflect.on(person).callDeclared("sayHello");
    }

}
