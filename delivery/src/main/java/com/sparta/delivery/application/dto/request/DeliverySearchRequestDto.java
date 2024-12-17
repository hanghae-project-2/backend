package com.sparta.delivery.application.dto.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record DeliverySearchRequestDto(
        int page,
        int limit,
        String searchValue,
        String searchType,
        String orderBy,
        Sort.Direction sort )
{
    public DeliverySearchRequestDto() {
        this(1, 10, null, null, "createdAt", Sort.Direction.DESC);
    }

    public Pageable getPageable() {
        return PageRequest.of(page - 1, limit, sort, orderBy);
    }
}
