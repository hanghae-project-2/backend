package com.sparta.delivery.infrastructure.message.consumer;

import com.sparta.delivery.application.event.DeliveryEvent;
import com.sparta.delivery.application.service.OrderEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {
    private static final String CREATED_ORDER_EVENT = "created-order-event-delivery";

    private final OrderEventService eventHandler;

    @KafkaListener(topics = CREATED_ORDER_EVENT,
            properties = "spring.json.value.default.type=com.sparta.delivery.application.event.DeliveryEvent")
    public void handleCreateOrderEvent(DeliveryEvent event) {
        eventHandler.createDelivery(event);
    }
}
