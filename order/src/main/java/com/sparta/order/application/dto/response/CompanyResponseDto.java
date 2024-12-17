package com.sparta.order.application.dto.response;

import java.util.UUID;

public record CompanyResponseDto(
        UUID companyId,
        String companyName,
        String address,
        UUID hubId) {
}
