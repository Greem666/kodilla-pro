package com.kodillapro.customconverter.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public HashTagBonanzaConverter hashTagBonanzaConverter() {
        return new HashTagBonanzaConverter();
    }
}
