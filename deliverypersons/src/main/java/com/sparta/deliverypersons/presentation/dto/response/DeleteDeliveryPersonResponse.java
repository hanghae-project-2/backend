package com.sparta.deliverypersons.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteDeliveryPersonResponse {
    private UUID deliveryPersonId;
    private UUID userId;
    private String message;

    public static DeleteDeliveryPersonResponse of(UUID deliveryPersonId, UUID userId) {
        return new DeleteDeliveryPersonResponse(
                deliveryPersonId,
                userId,
                "배송 담당자 삭제가 성공적으로 완료 되었습니다."
        );
    }
}
