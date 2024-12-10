package com.sparta.company.domain.exception

import org.springframework.http.HttpStatus

enum class Error(val status: HttpStatus, val message: String) {

    NOT_FOUND_COMPANY(HttpStatus.NOT_FOUND, "업체를 찾을 수 없습니다."),
}