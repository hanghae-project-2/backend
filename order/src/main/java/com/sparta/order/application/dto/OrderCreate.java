package com.sparta.order.application.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderCreate(
        UUID recipientCompanyId,
        UUID productId,
        Integer quantity,
        String specialRequests) {

}
