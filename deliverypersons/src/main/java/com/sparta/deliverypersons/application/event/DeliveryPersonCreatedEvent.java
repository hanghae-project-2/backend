package com.sparta.deliverypersons.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeliveryPersonCreatedEvent {
    private final UUID deliveryPersonId;
}