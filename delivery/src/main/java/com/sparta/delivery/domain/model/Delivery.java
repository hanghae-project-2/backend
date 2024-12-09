package com.sparta.delivery.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "p_deliveries")
public class Delivery {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus currentStatus;

    @Column(nullable = false)
    private Double actualDistance;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String receiverName;

    @Column(length = 255)
    private String receiverSlackId;

    @Column(nullable = false)
    private Double actualTime;

    @Column(nullable = false)
    private UUID deliveryPersonId;

    @Column(nullable = false)
    private UUID originHubId;

    @Column(nullable = false)
    private UUID destinationHubId;

}
