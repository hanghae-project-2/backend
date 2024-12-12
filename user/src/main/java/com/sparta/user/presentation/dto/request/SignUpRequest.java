package com.sparta.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "ID은(는) 비워둘 수 없습니다.")
    @Size(min = 3, max = 20, message = "최소 3자 이상, 최대 20자 이하입니다.")
    private String username;
    @NotBlank(message = "비밀번호은(는) 비워둘 수 없습니다.")
    @Size(min = 4, message = "비밀번호는 최소 4자 이상 입력해주세요.")
    private String password;
    @NotBlank(message = "Slack ID은(는) 비워둘 수 없습니다.")
    private String slackId;
}
