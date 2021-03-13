package com.kodillapro.ex2_1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Ex21Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfiguration.class);
        context.refresh();

        LibraryManager manager = context.getBean(LibraryManager.class);
        System.out.println("Context and beans are set up and ready to work");
        context.close();
    }

}
