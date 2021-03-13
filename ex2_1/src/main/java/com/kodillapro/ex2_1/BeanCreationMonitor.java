package com.kodillapro.ex2_1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanCreationMonitor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BeanCreationMonitor.class);

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info(String.format("Bean %s has been created", beanName));
        return bean;
    }
}
