package com.sparta.gateway.infrastructure.jwt;

import com.sparta.gateway.application.exception.token.ExpiredTokenException;
import com.sparta.gateway.application.exception.token.InvalidTokenException;
import com.sparta.gateway.application.exception.token.RegisteredInBlackListException;
import com.sparta.gateway.application.exception.token.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenParser {

	private static final String TOKEN_TYPE = "Bearer ";
	private static final String USER_KEY = "userId";
	private static final String AUTHORITY_KEY = "role";

	private final RedisService redisService;

	private SecretKey key;

	@Value("${SECURITY_JWT_KEY}")
	private String secretKey;

	@PostConstruct
	public void init() {
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	public String getJwt(ServerHttpRequest request) {

		try {
			String jwt = Optional.ofNullable(request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
					.orElseThrow(NullPointerException::new);

			if (!jwt.startsWith(TOKEN_TYPE)) {
				throw new InvalidTokenException(jwt);
			}

			return jwt.substring(TOKEN_TYPE.length());
		} catch (NullPointerException e) {
			throw new UnauthorizedException();
		}
	}

	public String getUserId(String token) {
		return getClaims(token, USER_KEY);
	}

	public String getUserRole(String token) {
		return getClaims(token, AUTHORITY_KEY);
	}

	private String getClaims(String token, String authorityKey) {
		try {
			if (redisService.isBlackList(token)) {
				throw new RegisteredInBlackListException(token);
			}

			Claims accessTokenClaims = parseClaims(token);

			return accessTokenClaims.get(authorityKey, String.class);
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException(token);
		} catch (RegisteredInBlackListException e) {
			throw new RegisteredInBlackListException(token);
		} catch (Exception e) {
			throw new InvalidTokenException(token);
		}
	}

	private Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException(token);
		} catch (Exception e) {
			throw new InvalidTokenException(token);
		}
	}

}
