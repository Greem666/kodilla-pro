package com.kodillapro.ex2_2.controller.publisher;

import com.kodillapro.ex2_2.controller.CalculatorController;
import com.kodillapro.ex2_2.event.CalculationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class CalculatorEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;
    private CalculatorController source;

    public CalculatorEventPublisher(CalculatorController source) {
        this.source = source;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publishEvent(String methodName, double a, double b) {
        publisher.publishEvent(
                new CalculationEvent(
                        source,
                        methodName,
                        a, b
                )
        );
    }

}
