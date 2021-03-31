package com.kodillapro.ex3_3.controller;

import com.kodillapro.ex3_3.domain.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping(path = "/new")
    public void getOrder(@RequestBody OrderDto orderDto) {
        jmsTemplate.convertAndSend("order-queue", orderDto);
    }
}
