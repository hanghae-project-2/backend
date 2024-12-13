package com.sparta.deliverypersons.domain.model;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name = "delivery_persons")
public class DeliveryPersons extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "hub_id")
    private UUID hubId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType type;

    @Column(name = "delivery_sequence")
    private int deliverySequence;
}
