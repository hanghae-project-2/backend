package com.sparta.order.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당하는 주문을 찾을 수 없습니다. ");

    private final HttpStatus status;
    private final String message;
}
