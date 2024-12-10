package com.sparta.order.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderResponse(
        UUID orderId,
        UUID deliveryId
){


}
