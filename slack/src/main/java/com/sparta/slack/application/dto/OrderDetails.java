package com.sparta.slack.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDetails {

    @Getter
    public static class Response {
        private final UUID orderId;
        private final UUID productId;
        private final UUID requestCompanyId;
        private final String requestCompanyName;
        private final UUID recipientCompanyId;
        private final String recipientCompanyName;
        private final String productName;
        private final Integer quantity;
        private final Integer price;
        private final Integer totalPrice;
        private final UUID deliveryId;
        private final String orderStatus;
        private final String specialRequests;
        private final LocalDateTime createdAt;
        private final LocalDateTime updatedAt;

        public Response(UUID orderId, UUID productId, UUID requestCompanyId, String requestCompanyName, UUID recipientCompanyId, String recipientCompanyName, String productName, Integer quantity, Integer price, Integer totalPrice, UUID deliveryId, String orderStatus, String specialRequests, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.orderId = orderId;
            this.productId = productId;
            this.requestCompanyId = requestCompanyId;
            this.requestCompanyName = requestCompanyName;
            this.recipientCompanyId = recipientCompanyId;
            this.recipientCompanyName = recipientCompanyName;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
            this.totalPrice = totalPrice;
            this.deliveryId = deliveryId;
            this.orderStatus = orderStatus;
            this.specialRequests = specialRequests;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }

}
