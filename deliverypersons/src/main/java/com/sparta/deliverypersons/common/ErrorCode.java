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
    HUB_ID_REQUIRED("허브 ID는 필수입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
