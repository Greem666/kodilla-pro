package com.kodillapro.ex2_2.controller;

import com.kodillapro.ex2_2.controller.publisher.CalculatorEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/calculate")
public class CalculatorController {

    @Autowired
    private CalculatorEventPublisher publisher;

    @GetMapping(path = "add")
    public double add(@RequestParam double a, @RequestParam double b) {
        publisher.publishEvent("add", a, b);
        return a + b;
    }

    @GetMapping(path = "subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        publisher.publishEvent("subtract", a, b);
        return a - b;
    }

    @GetMapping(path = "multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        publisher.publishEvent("multiply", a, b);
        return a * b;
    }

    @GetMapping(path = "divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        publisher.publishEvent("divide", a, b);
        return a / b;
    }


}
