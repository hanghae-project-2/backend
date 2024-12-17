package com.sparta.order.application.dto.request;

import com.sparta.order.domain.model.OrderStatus;

public record OrderUpdateRequestDto(
        OrderStatus orderStatus,
        Integer quantity,
        String specialRequests
) {
}
