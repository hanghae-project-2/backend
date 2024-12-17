package com.sparta.deliverypersons.infrastructure.messaging.producer;

import com.sparta.deliverypersons.application.event.DeliveryPersonCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventProducer {

    private final KafkaTemplate<String, DeliveryPersonCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "delivery-person-created-topic";

    public void sendDeliveryPersonCreatedEvent(DeliveryPersonCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
