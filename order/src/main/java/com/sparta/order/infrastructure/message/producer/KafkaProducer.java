package com.sparta.order.infrastructure.message.producer;

import com.sparta.order.application.dto.CreateOrderEventDto;
import com.sparta.order.application.dto.DeliveryEventDto;
import com.sparta.order.application.dto.ProductEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String DELIVERY_TOPIC = "created-order-event-delivery";
    private static final String PRODUCT_TOPIC = "created-order-event-product";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CreateOrderEventDto event) {

        ProductEventDto product = ProductEventDto.builder()
                        .productId(event.productId()).quantity(event.quantity()).build();

        DeliveryEventDto delivery = DeliveryEventDto.builder()
                .orderId(event.orderId()).build();

        kafkaTemplate.send(DELIVERY_TOPIC, delivery);
        kafkaTemplate.send(PRODUCT_TOPIC, product);

    }
}
