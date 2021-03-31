package com.kodillapro.ex3_3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class Ex33Application {

    public static void main(String[] args) {
        SpringApplication.run(Ex33Application.class, args);
    }

}
