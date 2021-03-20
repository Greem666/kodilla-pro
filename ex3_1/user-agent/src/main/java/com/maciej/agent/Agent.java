package com.maciej.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("User agent is running.");

        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.maciej.user"))
                .transform(
                        (((builder, typeDescription, classLoader, module) -> {
                            return builder.visit(Advice.to(DoSomethingMethodMonitor.class).on(ElementMatchers.any()));
                        }))
                );
        agentBuilder.installOn(instrumentation);
    }
}
