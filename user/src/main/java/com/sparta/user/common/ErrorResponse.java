package com.sparta.user.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
}
