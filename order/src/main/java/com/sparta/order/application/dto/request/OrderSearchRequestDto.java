package com.sparta.order.application.dto.request;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public record OrderSearchRequestDto(
        int page,                  // 페이지 번호
        int limit,                 // 한 페이지에 보여지는 데이터 수
        String searchValue,        // 검색 키워드
        String searchType,         // 검색 유형 (e.g., ORDER_ID, RECIPIENT_NAME)
        String orderBy,            // 정렬에 사용되는 필드
        Sort.Direction sort )
{
    public OrderSearchRequestDto() {
        this(1, 10, null, null, "createdAt", Sort.Direction.DESC);
    }

    // 페이지 객체 생성
    public Pageable getPageable() {
        return PageRequest.of(page - 1, limit, sort, orderBy);
    }
}
