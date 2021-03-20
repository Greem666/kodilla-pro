package com.maciej.user;

import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        User user = new User();

        for (int n = 0; n < random.nextInt(20); n++) {
            user.doSomething();
        }
    }
}
