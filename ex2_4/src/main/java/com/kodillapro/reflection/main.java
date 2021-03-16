package com.kodillapro.reflection;

import java.lang.reflect.Field;

public class main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<Student> descriptor = Student.class;

        Student student = new Student();

        Field indexNumberField = descriptor.getDeclaredField("indexNumber");
        indexNumberField.setAccessible(true);

        System.out.println(indexNumberField.get(student));
    }
}
