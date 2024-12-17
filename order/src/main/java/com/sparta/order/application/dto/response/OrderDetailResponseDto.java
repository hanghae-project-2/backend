package com.sparta.order.application.dto.response;

import com.sparta.order.domain.model.Order;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OrderDetailResponseDto(
        UUID orderId,
        UUID productId,
        UUID requestCompanyId,
        String requestCompanyName,
        UUID recipientCompanyId,
        String recipientCompanyName,
        String productName,
        Integer quantity,
        Integer price,
        Integer totalPrice,
        UUID deliveryId,
        String orderStatus,
        String specialRequests,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {


    public static OrderDetailResponseDto from(Order order, CompanyResponseDto recipientCompany, CompanyResponseDto requestCompany, ProductInfoResponseDto product, UUID deliveryId) {
        return new OrderDetailResponseDto(
                order.getId(),
                product.productId(),
                requestCompany.companyId(),
                requestCompany.companyName(),
                recipientCompany.companyId(),
                recipientCompany.companyName(),
                product.name(),
                order.getQuantity(),
                order.getPrice(),
                order.getTotalPrice(),
                deliveryId,
                order.getStatus().toString(),
                order.getSpecialRequests(),
                order.getCreatedAt(),
                order.getUpdatedAt()
        );
    }
}
