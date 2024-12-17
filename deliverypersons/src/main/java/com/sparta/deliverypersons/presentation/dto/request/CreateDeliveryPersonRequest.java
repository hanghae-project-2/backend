package com.sparta.deliverypersons.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeliveryPersonRequest {
    private UUID userId;
    private UUID hubId;
    private String deliveryType;
}
