package com.sparta.order.domain.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OrderException extends RuntimeException{
    private final Error error;

}
