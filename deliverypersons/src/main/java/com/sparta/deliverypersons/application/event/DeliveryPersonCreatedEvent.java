package com.sparta.deliverypersons.application.event;

import java.util.UUID;

public record DeliveryPersonCreatedEvent(UUID deliveryPersonId) {
}