package com.sparta.order.infrastructure.message.producer;

import com.sparta.order.application.event.CreateOrderEvent;
import com.sparta.order.application.event.DeleteEvent;
import com.sparta.order.application.event.DeliveryEvent;
import com.sparta.order.application.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String DELETED_DELIVERY_TOPIC = "deleted-order-event";
    private static final String DELIVERY_TOPIC = "created-order-event-delivery";
    private static final String PRODUCT_TOPIC = "created-order-event-product";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CreateOrderEvent event) {

        ProductEvent product = ProductEvent.builder()
                        .productId(event.productId()).quantity(event.quantity()).build();

        DeliveryEvent delivery = DeliveryEvent.builder()
                .orderId(event.orderId()).address(event.address())
                        .startHubId(event.startHubId()).endHubId(event.endHubId())
                .createdBy(event.createdBy()).build();

        kafkaTemplate.send(DELIVERY_TOPIC, delivery);
        kafkaTemplate.send(PRODUCT_TOPIC, product);

    }

    public void delete(DeleteEvent target){
        kafkaTemplate.send(DELETED_DELIVERY_TOPIC, target);
    }

}
