package com.deen.order_service.service;

import com.deen.order_service.dto.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String,Object> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void publishOrderCreated(OrderCreatedEvent event){
        kafkaTemplate.send(
                "order-events",
                event
        );
        System.out.println("OrderCreatedEvent Published: "+event.getOrderId());
    }
}
