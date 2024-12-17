package com.sparta.gateway.application.exception.token;

import com.sparta.gateway.application.exception.Error;
import com.sparta.gateway.application.exception.TokenException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisteredInBlackListException extends TokenException {
	String token;

	public RegisteredInBlackListException(String token) {
		super(Error.REGISTERED_IN_BLACKLIST, HttpStatus.UNAUTHORIZED);
		this.token = token;
	}
}
