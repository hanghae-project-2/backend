package com.sparta.deliverypersons.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public static DeliveryPersons create(UUID userId, UUID hubId, DeliveryType type, int sequence) {
        DeliveryPersons deliveryPerson = new DeliveryPersons();
        deliveryPerson.userId = userId;
        deliveryPerson.hubId = type == DeliveryType.HUB_DELIVERY ? null : hubId;
        deliveryPerson.type = type;
        deliveryPerson.deliverySequence = sequence;
        deliveryPerson.createdBy = userId;
        return deliveryPerson;
    }

    public void update(UUID hubId, DeliveryType type, UUID updatedBy) {
        if (type == DeliveryType.HUB_DELIVERY) {
            this.hubId = null; // 허브 배송 담당자의 경우 허브 ID를 null로 설정
        } else {
            this.hubId = hubId;
        }
        this.type = type;
        this.updatedBy = updatedBy;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete(UUID deletedBy) {
        this.isDelete = true;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = deletedBy;
    }
}
