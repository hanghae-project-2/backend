package com.sparta.order.application.dto.response;

import com.sparta.order.domain.model.Order;
import com.sparta.order.domain.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderListResponseDto(
        UUID orderId,
        UUID productId,
        String productName,
        Integer quantity,
        UUID recipientCompanyId,
        String recipientCompanyName,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
    public static OrderListResponseDto from(Order order, CompanyResponseDto recipientCompany, ProductResponseDto product) {
        return new OrderListResponseDto(
                order.getId(),
                order.getProductId(),
                product.productName(),
                order.getQuantity(),
                order.getRecipientCompanyId(),
                recipientCompany.companyName(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
