package com.sparta.gateway.infrastructure.filter;

import com.sparta.gateway.application.exception.Error;
import com.sparta.gateway.application.exception.token.RegisteredInBlackListException;
import com.sparta.gateway.application.exception.token.UnauthorizedException;
import com.sparta.gateway.infrastructure.jwt.TokenParser;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.sparta.gateway.presentation.handler.FilterExceptionHandler.createErrorResponse;

@Component
@RequiredArgsConstructor
public class GatewayHeaderFilter implements GlobalFilter, Ordered {

	private static final String[] EXCLUDED_PATHS = {"/", "/auth/signUp", "/auth/signIn", "/swagger-ui/**"};
	private final TokenParser tokenParser;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		String path = exchange.getRequest().getURI().getPath();

		for (String excludedPath : EXCLUDED_PATHS) {
			if (path.equals(excludedPath)) {
				return chain.filter(exchange);
			}
		}

		try {

			String jwt = tokenParser.getJwt(exchange.getRequest());

			return chain.filter(
					exchange.mutate().request(
							exchange.getRequest().mutate()
									.header("Authenticated-User-Id", tokenParser.getUserId(jwt))
									.header("Authenticated-User-Role", tokenParser.getUserRole(jwt))
									.build()
					).build()
			);
		} catch (ExpiredJwtException e) {
			return createErrorResponse(exchange.getResponse(), Error.EXPIRED_TOKEN);
		} catch (RegisteredInBlackListException e) {
			return createErrorResponse(exchange.getResponse(), Error.REGISTERED_IN_BLACKLIST);
		} catch (UnauthorizedException e) {
			return createErrorResponse(exchange.getResponse(), Error.UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
			return createErrorResponse(exchange.getResponse(), Error.INVALID_TOKEN);
		}
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}
}
