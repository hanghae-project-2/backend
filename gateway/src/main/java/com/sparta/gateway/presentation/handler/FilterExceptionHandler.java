package com.sparta.gateway.presentation.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gateway.application.exception.Error;
import com.sparta.gateway.application.exception.token.JsonParsingException;
import com.sparta.gateway.presentation.response.Response;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class FilterExceptionHandler {

	public static Mono<Void> createErrorResponse(ServerHttpResponse response, Error error) {

		response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
		response.getHeaders().add(org.springframework.http.HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		Response<Void> customResponse = Response.<Void>builder()
				.code(error.getCode())
				.message(error.getMessage())
				.build();

		String json;

		try {
			json = new ObjectMapper().writeValueAsString(customResponse);
		} catch (JsonProcessingException e) {
			throw new JsonParsingException();
		}

		byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(bytes);

		return response.writeWith(Mono.just(buffer));
	}

}
