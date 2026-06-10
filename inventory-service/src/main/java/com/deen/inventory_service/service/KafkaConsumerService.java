package com.deen.inventory_service.service;

import com.deen.inventory_service.dto.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "order-events",
            groupId = "inventory-group"
    )

    public void consumerOrderCreated(
            OrderCreatedEvent event
    ){
        System.out.println(
                "Received OrderCreatedEvent : "+ event.getOrderId()
        );

        System.out.println(
                "Product: "+ event.getProductId()
        );

        System.out.println(
                "Quantity : "+ event.getQuantity()
        );


    }
}
