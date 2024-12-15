package com.sparta.deliverypersons.infrastructure.messaging.producer;

import com.sparta.deliverypersons.application.event.DeliveryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventProducer {

    private final KafkaTemplate<String, DeliveryEvent> kafkaTemplate;

    public void sendDeliveryEvent(DeliveryEvent event) {
        kafkaTemplate.send("delivery_created", event);
    }
}
