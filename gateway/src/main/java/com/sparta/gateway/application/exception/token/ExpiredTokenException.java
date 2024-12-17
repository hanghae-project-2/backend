package com.sparta.gateway.application.exception.token;

import com.sparta.gateway.application.exception.Error;
import com.sparta.gateway.application.exception.TokenException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExpiredTokenException extends TokenException {

	String token;

	public ExpiredTokenException(String token) {
		super(Error.EXPIRED_TOKEN, HttpStatus.UNAUTHORIZED);
		this.token = token;
	}
}
