package com.maciej.agent;

import net.bytebuddy.asm.Advice;

public class DoSomethingMethodMonitor {

    @Advice.OnMethodEnter
    public static void enter(@Advice.Origin Class clazz, @Advice.Origin("#m") String methodName) {
        System.out.println("Method doSomething() has been called...");
    }

    @Advice.OnMethodExit
    public static void exit() {
        System.out.println("Done!");
    }
}
