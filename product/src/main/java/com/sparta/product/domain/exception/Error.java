package com.sparta.product.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_PRODUCT(404,HttpStatus.NOT_FOUND, "해당하는 제품을 찾을 수 없습니다. ");

    private final int code;
    private final HttpStatus status;
    private final String message;
}
