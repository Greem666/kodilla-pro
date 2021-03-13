package com.kodillapro.ex2_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public BeanMonitor beanMonitor() {
        return new BeanMonitor();
    }

    @Bean
    public BeanCreationMonitor beanCreationMonitor() {
        return new BeanCreationMonitor();
    }

    @Bean
    public LibraryManager libraryManager() {
        return new LibraryManager();
    }
}
