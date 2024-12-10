package com.sparta.hub.domain.exception

import org.springframework.http.HttpStatus

enum class Error(val status: HttpStatus, val message: String) {

    NOT_FOUND_HUB(HttpStatus.NOT_FOUND, "허브를 찾을 수 없습니다."),
    UNABLE_CALCULATE_ROUTE(HttpStatus.BAD_REQUEST, "경로를 계산할 수 없습니다."),
}