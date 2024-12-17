package com.sparta.order.application.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderResponseDto(
        UUID orderId,
        UUID deliveryId
){


}
