package com.sparta.delivery.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DeliveryException extends RuntimeException{
    private final Error error;

}
