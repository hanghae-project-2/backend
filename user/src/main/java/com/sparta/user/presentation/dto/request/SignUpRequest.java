package com.sparta.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "ID는 비워둘 수 없습니다.")
    @Size(min = 4, max = 10, message = "ID는 최소 4자 이상, 최대 10자 이하입니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "ID는 알파벳 소문자(a~z)와 숫자(0~9)만 포함할 수 있습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 최대 15자 이하입니다.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[a-zA-Z\\d!@#$%^&*(),.?\":{}|<>]+$",
            message = "비밀번호는 알파벳 대소문자, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "Slack ID는 비워둘 수 없습니다.")
    private String slackId;
}
