package com.kodillapro.ex2_2.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CalculationEvent extends ApplicationEvent {

    protected double a;
    protected double b;
    protected String methodName;

    public CalculationEvent(Object source, String methodName, double a, double b) {
        super(source);
        this.a = a;
        this.b = b;
        this.methodName = methodName;
    }
}
