package com.sparta.deliverypersons.presentation.dto.response;

import com.sparta.deliverypersons.domain.model.DeliveryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateDeliveryPersonResponse {
    private UUID hubId;
    private DeliveryType deliveryType;

    public static UpdateDeliveryPersonResponse fromEntity(UUID hubId, DeliveryType deliveryType) {
        return new UpdateDeliveryPersonResponse(hubId, deliveryType);
    }
}
