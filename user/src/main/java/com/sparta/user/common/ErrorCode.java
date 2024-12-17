package com.sparta.user.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // 사용자 관련 에러
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_ALREADY_APPROVED("사용자가 이미 승인되었습니다.", HttpStatus.BAD_REQUEST),
    USER_ALREADY_DELETED("이미 삭제된 사용자입니다.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD("현재 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    MASTER_ROLE_CANNOT_BE_MODIFIED("MASTER 권한은 수정할 수 없습니다.", HttpStatus.BAD_REQUEST),
    MASTER_ACCESS_REQUIRED("MASTER 권한이 있어야 이 작업을 수행할 수 있습니다.", HttpStatus.FORBIDDEN),
    SELF_OR_MASTER_ACCESS_REQUIRED("자신의 정보 또는 MASTER 권한만 수정할 수 있습니다.", HttpStatus.FORBIDDEN),
    ACCOUNT_DELETED("삭제된 계정입니다.", HttpStatus.FORBIDDEN),
    ACCOUNT_NOT_APPROVED("승인되지 않은 계정입니다.", HttpStatus.FORBIDDEN),
    DUPLICATE_USERNAME("중복된 Username입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_SLACK_ID("중복된 SlackId입니다.", HttpStatus.BAD_REQUEST),

    // 인증 관련 에러
    INVALID_OR_EXPIRED_TOKEN("유효하지 않거나 만료된 토큰입니다.", HttpStatus.UNAUTHORIZED),
    UNKNOWN_TOKEN_TYPE("토큰 타입이 확인되지 않았습니다.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED("권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN_ACCESS("접근이 금지되었습니다.", HttpStatus.FORBIDDEN);

    private final String message;
    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
