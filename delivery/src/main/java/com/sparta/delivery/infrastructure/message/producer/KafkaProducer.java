package com.sparta.delivery.infrastructure.message.producer;

import com.sparta.delivery.application.event.CreateDeliveryEvent;
import com.sparta.delivery.application.event.DeliveryRouteEvent;
import com.sparta.delivery.application.event.SlackEvent;
import com.sparta.order.application.event.CreateOrderEvent;
import com.sparta.order.application.event.DeliveryEvent;
import com.sparta.order.application.event.ProductEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private static final String DELIVERY_ROUTE_TOPIC = "created-delivery-event-delivery-route";
    private static final String SLACK_TOPIC = "created-delivery-event-slack";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CreateDeliveryEvent event) {

        DeliveryRouteEvent deliveryRoute = DeliveryRouteEvent.builder()
                        .deliveryId(event.deliveryId())
                                .endHubId(event.endHubId())
                                        .startHubId(event.startHubId()).build();

        //TODO: 추후 변수 생기면 코드 추가
        SlackEvent slack = SlackEvent.builder().build();

        kafkaTemplate.send(DELIVERY_ROUTE_TOPIC, deliveryRoute);
        kafkaTemplate.send(SLACK_TOPIC, slack);

    }
}
