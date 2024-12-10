package com.sparta.order.application.dto;

import com.sparta.order.domain.model.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class OrderDetail {
    private UUID orderId;
    private UUID productId;
    private UUID requestCompanyId;
    private String requestCompanyName;
    private UUID recipientCompanyId;
    private String recipientCompanyName;
    private String productName;
    private Integer quantity;
    private UUID deliveryId;
    private OrderStatus orderStatus;
    private String specialRequests;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
