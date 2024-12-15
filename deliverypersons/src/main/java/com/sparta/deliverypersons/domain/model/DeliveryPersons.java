package com.sparta.deliverypersons.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "delivery_persons")
@Getter
@NoArgsConstructor
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

    public static DeliveryPersons create(UUID userId, UUID hubId, DeliveryType type, int sequence) {
        DeliveryPersons deliveryPerson = new DeliveryPersons();
        deliveryPerson.userId = userId;
        deliveryPerson.hubId = type == DeliveryType.HUB_DELIVERY ? null : hubId;
        deliveryPerson.type = type;
        deliveryPerson.deliverySequence = sequence;
        return deliveryPerson;
    }
}
