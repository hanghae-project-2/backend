package com.sparta.order.presentation.api.controller.docs;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.application.dto.request.OrderUpdateRequestDto;
import com.sparta.order.application.dto.response.OrderDetailResponseDto;
import com.sparta.order.application.dto.response.OrderListResponseDto;
import com.sparta.order.application.dto.response.OrderResponseDto;
import com.sparta.order.application.dto.response.PageResponseDto;
import com.sparta.order.presentation.api.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Order", description = "주문 API")
public abstract class OrderControllerDocs {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "주문 생성 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 주문를 찾을 수 없습니다.\n
                        | - Order 서버와 통신이 불가능한 경우\n
                        | - OrderId에 해당하는 주문이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Order에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = """
                        | 1. 재고 부족으로 주문할 수 없습니다.\n
                        | - Product에 주문할 수 있는 재고가 부족한 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            )
    })
    @Operation(summary = "주문 생성", description = "주문 생성 API")
    @PostMapping("/orders")
    public abstract Response<UUID> createOrder(@RequestBody OrderCreateRequestDto requestDto, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "주문 삭제 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 주문를 찾을 수 없습니다.\n
                        | - Order 서버와 통신이 불가능한 경우\n
                        | - OrderId에 해당하는 주문이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Order에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
    })
    @Operation(summary = "주문 삭제", description = "주문 삭제 API")
    @DeleteMapping("/orders/{orderId}")
    public abstract Response<UUID> deleteOrder(@PathVariable java.util.UUID orderId, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "주문 조회 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 주문를 찾을 수 없습니다.\n
                        | - Order 서버와 통신이 불가능한 경우\n
                        | - OrderId에 해당하는 주문이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Order에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
    })
    @Operation(summary = "주문 조회(단건)", description = "주문 조회 API")
    @GetMapping("/orders/{orderId}")
    public abstract Response<OrderDetailResponseDto> getOrderById(@PathVariable java.util.UUID orderId, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "주문 수정 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 주문를 찾을 수 없습니다.\n
                        | - Order 서버와 통신이 불가능한 경우\n
                        | - OrderId에 해당하는 주문이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Order에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
    })
    @Operation(summary = "주문 수정", description = "주문 수정 API")
    @PatchMapping("/orders/{orderId}")
    public abstract Response<UUID> updateOrder(@PathVariable java.util.UUID orderId, @RequestBody OrderUpdateRequestDto requestDto, HttpServletRequest servletRequest);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "주문 조회, 검색 성공",
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = """
                        | 1. 해당하는 주문를 찾을 수 없습니다.\n
                        | - Order 서버와 통신이 불가능한 경우\n
                        | - OrderId에 해당하는 주문이 존재하지 않는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = """
                        | 1. 접근 권한이 없습니다.\n
                        | - Order에 접근 할 수 있는 권한이 없는 경우\n
                        """,
                    content = @Content(schema = @Schema(implementation = Response.class))
            ),
    })
    @Operation(summary = "주문 목록 조회 및 검색", description = "주문 목록 조회 및 검색 API")
    @GetMapping("/orders")
    public abstract Response<PageResponseDto<OrderListResponseDto>>getOrders(@ModelAttribute OrderSearchRequestDto requestDto, HttpServletRequest servletRequest);

}
