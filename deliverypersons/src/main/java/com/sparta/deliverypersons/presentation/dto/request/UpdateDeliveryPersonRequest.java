package com.sparta.deliverypersons.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryPersonRequest {
    private UUID hubId;
    private String type;
}
