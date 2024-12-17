package com.sparta.delivery.presentation.api.controller.docs;

import com.sparta.delivery.application.dto.request.DeliveryComplateRequestDto;
import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.application.dto.response.DeliveryDetailResponseDto;
import com.sparta.delivery.application.dto.response.PageResponseDto;
import com.sparta.delivery.domain.model.DeliveryStatus;
import com.sparta.delivery.presentation.api.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Delivery", description ="배송 API" )
public abstract class DeliveryControllerDocs {


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "배송 조회 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 배송를 찾을 수 없습니다.\n
                        | - Delivery 서버와 통신이 불가능한 경우\n
                        | - DeliveryId에 해당하는 배송이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Delivery에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "배송 조회(단건)", description ="배송 조회 API"  )
    @GetMapping("/deliveries/{deliveryId}")
    public abstract Response<DeliveryDetailResponseDto> getDeliveryById(@PathVariable UUID deliveryId, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "배송 상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 배송를 찾을 수 없습니다.\n
                        | - Delivery 서버와 통신이 불가능한 경우\n
                        | - DeliveryId에 해당하는 배송이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Delivery에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "배송 상태 수정", description = "배송 상태 수정 API")
    @PatchMapping("/deliveries/{deliveryId}")
    public abstract Response<UUID> updateDelivery(@PathVariable UUID deliveryId, @RequestBody DeliveryStatus status, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "배송 삭제 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 배송를 찾을 수 없습니다.\n
                        | - Delivery 서버와 통신이 불가능한 경우\n
                        | - DeliveryId에 해당하는 배송이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Delivery에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "배송 삭제", description = "배송 삭제 API")
    @DeleteMapping("/deliveries/{deliveryId}")
    public abstract Response<UUID> deleteDelivery(@PathVariable UUID deliveryId, HttpServletRequest servletRequest);


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "배송 조회, 검색 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "배송 목록 조회(검색)", description ="배송 목록 조회(검색) API" )
    @GetMapping("/deliveries")
    public abstract Response<PageResponseDto<DeliveryListResponseDto>> getDeliveries(@ModelAttribute DeliverySearchRequestDto requestDto, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "배송 완료 반영 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 배송를 찾을 수 없습니다.\n
                        | - Delivery 서버와 통신이 불가능한 경우\n
                        | - DeliveryId에 해당하는 배송이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Delivery에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "배송 완료(실제 시간, 거리 반영)", description = "배송 완료(실제 시간, 거리 반영) API")
    @PatchMapping("/deliveries/{deliveryId}/completed")
    public abstract Response<UUID> complateDelivery(@PathVariable UUID deliveryId, DeliveryComplateRequestDto requestDto, HttpServletRequest servletRequest);


}
