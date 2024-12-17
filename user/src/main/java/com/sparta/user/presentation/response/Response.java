package com.sparta.user.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(int code, String message, T data) {
}
