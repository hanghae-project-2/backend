package com.sparta.order.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Error {

    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당하는 주문을 찾을 수 없습니다. "),
    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고 부족으로 주문할 수 없습니다. "),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");;

    private final HttpStatus status;
    private final String message;
}
