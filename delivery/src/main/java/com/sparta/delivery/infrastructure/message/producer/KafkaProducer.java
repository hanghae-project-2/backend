package com.sparta.delivery.infrastructure.message.producer;

import com.sparta.delivery.application.event.CreateDeliveryEvent;
import com.sparta.delivery.application.event.DeleteEvent;
import com.sparta.delivery.application.event.DeliveryRouteEvent;
import com.sparta.delivery.application.event.SlackEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {


    private static final String DELETED_DELIVER_ROUTE_TOPIC = "deleted-delivery-event";
    private static final String DELIVERY_ROUTE_TOPIC = "created-delivery-event-delivery-route";
    private static final String SLACK_TOPIC = "created-delivery-event-slack";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(CreateDeliveryEvent event) {

        DeliveryRouteEvent deliveryRoute = new DeliveryRouteEvent(
                event.deliveryId(), event.startHubId(), event.endHubId()
        );

        SlackEvent slack = new SlackEvent(
                event.recipientSlackId(),
                event.deliveryAddress(),
                event.deliveryPersonId(),
                event.recipientName(),
                event.startHubId(),
                event.endHubId(),
                event.orderId()
        );

        kafkaTemplate.send(DELIVERY_ROUTE_TOPIC, deliveryRoute);
        kafkaTemplate.send(SLACK_TOPIC, slack);

    }

    public void delete(DeleteEvent target) {
        kafkaTemplate.send(DELETED_DELIVER_ROUTE_TOPIC, target);
    }
}
