package com.sparta.company.domain.exception

import org.springframework.http.HttpStatus

enum class Error(val status: HttpStatus, val message: String) {

    NOT_FOUND_HUB(HttpStatus.NOT_FOUND, "허브를 찾을 수 없습니다."),

    NOT_FOUND_COMPANY(HttpStatus.NOT_FOUND, "업체를 찾을 수 없습니다."),
    INCORRECT_COMPANY_TYPE(HttpStatus.BAD_REQUEST, "잘못된 업체 타입입니다."),
    INCORRECT_HUB_ID(HttpStatus.BAD_REQUEST, "잘못된 허브 ID 입니다."),

    CIRCUIT_BREAKER_OPEN(HttpStatus.BAD_REQUEST, "서비스가 일시적으로 사용 불가능합니다."),
    SERVER_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "응답 시간을 초과하였습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생하였습니다.")
}