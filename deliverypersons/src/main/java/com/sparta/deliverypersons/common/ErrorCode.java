package com.sparta.deliverypersons.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 공통 에러
    FORBIDDEN_ACTION("수정 또는 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN),

    // 배송 담당자 관련 에러
    DELIVERY_PERSON_NOT_FOUND("배송 담당자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_DELIVERY_TYPE("잘못된 배송 타입입니다.", HttpStatus.BAD_REQUEST),
    HUB_ID_REQUIRED("허브 ID는 필수입니다.", HttpStatus.BAD_REQUEST),
    MAX_HUB_DELIVERY_PERSONS_REACHED("허브 배송 담당자는 최대 10명까지만 생성할 수 있습니다.", HttpStatus.BAD_REQUEST),
    MAX_COMPANY_DELIVERY_PERSONS_REACHED("업체 배송 담당자는 각 허브 별로 최대 10명까지만 생성할 수 있습니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NO_HUBS_AVAILABLE("사용자와 가까운 허브를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
