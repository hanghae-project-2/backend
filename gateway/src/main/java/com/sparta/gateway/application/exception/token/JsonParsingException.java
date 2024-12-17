package com.sparta.gateway.application.exception.token;

import com.sparta.gateway.application.exception.Error;
import com.sparta.gateway.application.exception.TokenException;
import org.springframework.http.HttpStatus;

public class JsonParsingException extends TokenException {
	public JsonParsingException() {
		super(Error.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
