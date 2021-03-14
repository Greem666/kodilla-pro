package com.kodillapro.ex2_2.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class CalculatorListener implements ApplicationListener<CalculationEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorListener.class);

    @Override
    public void onApplicationEvent(CalculationEvent event) {
        logger.info(constructMsg(event));
    }

    private String constructMsg(CalculationEvent event) {
        StringBuilder string = new StringBuilder();
        return string
                .append("Endpoint \"").append(event.getMethodName()).append("\" ")
                .append("has been called for the following values: ")
                .append("a = ").append(event.getA()).append(", ")
                .append("b = ").append(event.getB()).append(" ")
                .toString();
    }
}
