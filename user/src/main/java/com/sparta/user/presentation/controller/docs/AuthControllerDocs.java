package com.sparta.user.presentation.controller.docs;

import com.sparta.user.presentation.dto.request.SignInRequest;
import com.sparta.user.presentation.dto.request.SignUpRequest;
import com.sparta.user.presentation.dto.response.AuthResponse;
import com.sparta.user.presentation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth", description = "인증 및 사용자 관리 API")
public abstract class AuthControllerDocs {

    @Operation(summary = "로그인", description = "사용자가 로그인을 수행하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/signIn")
    public abstract Response<AuthResponse> signIn(@RequestBody SignInRequest request);

    @Operation(summary = "로그아웃", description = "사용자가 로그아웃을 수행하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping("/signOut")
    public abstract Response<String> logout();

    @Operation(summary = "회원가입", description = "새로운 사용자를 등록하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "400", description = "입력값이 유효하지 않습니다.")
    })
    @PostMapping("/signUp")
    public abstract Response<String> signUp(@RequestBody @Valid SignUpRequest request);

    @Operation(summary = "유저 검증", description = "특정 사용자의 존재 여부를 검증하는 API입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검증 성공",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping("/verify")
    public abstract Response<Boolean> verifyUser(@RequestParam(value = "username") String username);
}
