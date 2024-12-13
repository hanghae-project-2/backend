package com.sparta.user.presentation.dto.request;

import com.sparta.user.domain.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserApprovalRequest {
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
