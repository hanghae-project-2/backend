package com.sparta.user.presentation.controller.docs;

import com.sparta.user.presentation.dto.request.UpdateUserRequest;
import com.sparta.user.presentation.dto.request.UpdateUserRoleRequest;
import com.sparta.user.presentation.dto.request.UserApprovalRequest;
import com.sparta.user.presentation.dto.response.UserResponse;
import com.sparta.user.presentation.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "User", description = "유저 관리 API")
public abstract class UserControllerDocs {

    @Operation(summary = "유저 단일 조회", description = "특정 유저 정보를 조회하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 조회 성공",
                            content = @Content(schema = @Schema(implementation = Response.class)))
            }
    )
    @GetMapping("/{id}")
    public abstract Response<UserResponse> getUserById(@PathVariable UUID id);

    @Operation(summary = "유저 목록 조회", description = "전체 유저 목록을 조회하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 목록 조회 성공",
                            content = @Content(schema = @Schema(implementation = Response.class)))
            }
    )
    @GetMapping
    public abstract Response<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "유저 승인", description = "특정 유저를 승인하고 권한을 부여하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 승인 성공",
                            content = @Content(schema = @Schema(implementation = Response.class)))
            }
    )
    @PatchMapping("/{userId}/approval")
    public abstract Response<String> approveUser(
            @PathVariable UUID userId,
            @RequestBody UserApprovalRequest request);

    @Operation(summary = "유저 정보 수정", description = "유저 자신의 정보를 수정하거나 MASTER 권한자가 수정할 수 있는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 정보 수정 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "403", description = "접근 권한이 없습니다.")
            }
    )
    @PatchMapping("/{id}")
    public abstract Response<String> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request);

    @Operation(summary = "유저 권한 수정", description = "MASTER 권한자가 유저의 권한을 수정하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 권한 수정 성공",
                            content = @Content(schema = @Schema(implementation = Response.class))),
                    @ApiResponse(responseCode = "403", description = "MASTER 권한이 필요합니다.")
            }
    )
    @PatchMapping("/{id}/role")
    public abstract Response<String> updateUserRole(
            @PathVariable UUID id,
            @RequestBody UpdateUserRoleRequest request);

    @Operation(summary = "유저 삭제", description = "유저를 삭제하는 API입니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "유저 삭제 성공",
                            content = @Content(schema = @Schema(implementation = Response.class)))
            }
    )
    @DeleteMapping("/{id}")
    public abstract Response<String> deleteUser(@PathVariable UUID id);
}
