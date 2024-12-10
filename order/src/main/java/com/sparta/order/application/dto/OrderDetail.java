package com.sparta.order.application.dto;

import com.sparta.order.domain.model.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OrderDetail(
        UUID orderId,
        UUID productId,
        UUID requestCompanyId,
        String requestCompanyName,
        UUID recipientCompanyId,
        String recipientCompanyName,
        String productName,
        Integer quantity,
        UUID deliveryId,
        OrderStatus orderStatus,
        String specialRequests,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
