package com.sparta.user.common;

import com.sparta.user.application.service.UserService;
import com.sparta.user.infrastructure.security.CustomUserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UserStatusAspect {

    private final UserService userService;

    @Before("@annotation(com.sparta.user.common.annotation.CheckUserStatus)")
    public void checkUserStatus() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetailsImpl)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        CustomUserDetailsImpl userDetails = (CustomUserDetailsImpl) authentication.getPrincipal();
        UUID userId = userDetails.getId();

        boolean isActive = userService.isUserActive(userId);
        if (!isActive) {
            log.warn("비활성화된 사용자: {}", userId);
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }
}
