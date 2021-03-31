package com.kodillapro.ex3_3.receiver;

import com.kodillapro.ex3_3.domain.OrderDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReceiver {

    @JmsListener(containerFactory = "jmsFactory", destination = "order-queue")
    public void receiveOrder(OrderDto orderDto) {
        System.out.println("Received the following order: ");
        System.out.println("Item: " + orderDto.getItemName());
        System.out.println("Quantity: " + orderDto.getQuantity());
        System.out.println("Final price: " + orderDto.getQuantity() * orderDto.getPricePerItem());
    }
}
