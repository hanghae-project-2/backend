package com.sparta.deliverypersons.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateDeliveryPersonResponse {
    private final UUID deliveryPersonId;
    private final UUID userId;
    private final String message;
}
