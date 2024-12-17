package com.sparta.delivery.domain.model;

import com.sparta.delivery.application.dto.response.UserResponseDto;
import com.sparta.delivery.application.event.DeliveryEvent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_deliveries")
public class Delivery extends BaseEntity {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus currentStatus;

    private Integer actualDistance;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String recipientName;

    private UUID recipientSlackId;

    private Integer actualTime;

    @Column(nullable = false)
    private UUID deliveryPersonId;

    @Column(nullable = false)
    private UUID startHubId;

    @Column(nullable = false)
    private UUID endHubId;

    public void updateDelivery(DeliveryStatus status) {
        this.currentStatus =status;
    }

    public void deleteDelivery(UUID deletedBy) {
        super.delete(deletedBy);
    }

    public static Delivery create(DeliveryEvent event, UUID deliveryPersonId, UserResponseDto user){
        return Delivery.builder()
                .orderId(event.orderId())
                .currentStatus(DeliveryStatus.WAITING_AT_HUB)
                .deliveryAddress(event.address())
                .recipientName(user.username())
                .recipientSlackId(user.slackId())
                .deliveryPersonId(deliveryPersonId)
                .startHubId(event.startHubId())
                .endHubId(event.endHubId())
                .build();
    }
}
