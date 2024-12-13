package com.sparta.deliveryroute.infrastructure.messaging.consumer;

import com.sparta.deliveryroute.application.event.CreateDeliveryEvent;
import com.sparta.deliveryroute.application.service.DeliveryEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventConsumer {

	private static final String CREATED_DELIVERY_EVENT = "created-delivery-event";

	private final DeliveryEventService eventHandler;

	@KafkaListener(topics = CREATED_DELIVERY_EVENT,
			properties = "spring.json.value.default.type=com.sparta.deliveryroute.domain.event.CreateDeliveryEvent")
	public void handleCreateDeliveryEvent(CreateDeliveryEvent event) {
		eventHandler.CreateDeliveryRoutes(event);
	}
}
