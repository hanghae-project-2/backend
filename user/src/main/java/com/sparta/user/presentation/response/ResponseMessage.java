package com.sparta.user.presentation.response;

import lombok.Getter;

@Getter
public enum ResponseMessage {
    SIGN_UP_SUCCESS("회원가입 성공."),
    SIGN_IN_SUCCESS("로그인 성공."),
    LOGOUT_SUCCESS("로그아웃 성공."),
    USER_UPDATE_SUCCESS("회원 정보 수정 성공."),
    ROLE_UPDATE_SUCCESS("권한 수정 성공."),
    USER_DELETE_SUCCESS("회원 삭제 성공."),
    USER_APPROVED_SUCCESS("승인 및 권한이 부여되었습니다."),
    UNAUTHORIZED_ACTION("권한이 없어 작업을 수행할 수 없습니다.");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

}
