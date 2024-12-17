package com.sparta.deliverypersons.presentation.dto.response;

import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import com.sparta.deliverypersons.domain.model.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeliveryPersonResponse {
    private UUID deliveryPersonId;
    private UUID userId;
    private UUID hubId;
    private DeliveryType deliveryType;
    private int deliverySequence;
    private LocalDateTime createdAt;
    private UUID createdBy;

    public static DeliveryPersonResponse fromEntity(DeliveryPersons deliveryPerson) {
        return new DeliveryPersonResponse(
                deliveryPerson.getId(),
                deliveryPerson.getUserId(),
                deliveryPerson.getHubId(),
                deliveryPerson.getType(),
                deliveryPerson.getDeliverySequence(),
                deliveryPerson.getCreatedAt(),
                deliveryPerson.getCreatedBy()
        );
    }
}
