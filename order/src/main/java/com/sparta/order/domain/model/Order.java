package com.sparta.order.domain.model;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Table(name = "p_orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AuditingFields {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private UUID productId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(columnDefinition = "TEXT")
    private String specialRequests;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    private UUID recipientCompanyId;


    public void deleteOrder(UUID deletedBy) {
        super.delete(deletedBy);
    }

    public static Order create(OrderCreateRequestDto request) {
        return Order.builder()
                .recipientCompanyId(request.recipientCompanyId())
                .productId(request.productId())
                .quantity(request.quantity())
                .specialRequests(request.specialRequests())
                .build();
    }


}
