package com.sparta.user.application.dtos;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class AuthResponse {
    private String accessToken;

    public static AuthResponse of(String accessToken) {
        return AuthResponse.builder().accessToken(accessToken).build();
    }
}
