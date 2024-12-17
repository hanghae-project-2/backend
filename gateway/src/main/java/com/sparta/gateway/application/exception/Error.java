package com.sparta.gateway.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public enum Error {

	EXPIRED_TOKEN(9000, "GateWay : 토큰이 만료되었습니다."),
	INVALID_TOKEN(9001, "GateWay : 잘못된 토큰입니다."),
	UNAUTHORIZED(9002, "GateWay : 로그인이 필요합니다."),
	PERMISSION_DENIED(9003, "GateWay : 권한이 없습니다."),
	REGISTERED_IN_BLACKLIST(9004, "Gateway : 블랙리스트에 등록되어있는 토큰입니다."),
	INTERNAL_SERVER_ERROR(9999, "GateWay : 서버 오류입니다.");

	Integer code;
	String message;

}
