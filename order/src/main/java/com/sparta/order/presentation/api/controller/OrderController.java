package com.sparta.order.presentation.api.controller;

import com.sparta.order.application.dto.request.OrderCreateRequestDto;
import com.sparta.order.application.dto.request.OrderSearchRequestDto;
import com.sparta.order.application.dto.request.OrderUpdateRequestDto;
import com.sparta.order.application.dto.response.OrderDetailResponseDto;
import com.sparta.order.application.dto.response.OrderListResponseDto;
import com.sparta.order.application.dto.response.OrderResponseDto;
import com.sparta.order.application.dto.response.PageResponseDto;
import com.sparta.order.domain.service.OrderService;
import com.sparta.order.presentation.api.controller.docs.OrderControllerDocs;
import com.sparta.order.presentation.api.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController extends OrderControllerDocs {

    private final OrderService orderService;

    @PostMapping()
    @Override
    public Response<OrderResponseDto> createOrder(OrderCreateRequestDto requestDto, HttpServletRequest servletRequest) {
        return new Response<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), orderService.createOrder(requestDto, servletRequest));
    }



    @DeleteMapping("/{orderId}")
    @Override
    public Response<UUID> deleteOrder(@PathVariable UUID orderId, HttpServletRequest servletRequest) {
        return new Response<>(HttpStatus.NO_CONTENT.value(),HttpStatus.NO_CONTENT.getReasonPhrase(), orderService.deleteOrder(orderId, servletRequest));
    }

    @GetMapping("/{orderId}")
    @Override
    public Response<OrderDetailResponseDto> getOrderById(@PathVariable UUID orderId, HttpServletRequest servletRequest) {
        return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderService.getOrderById(orderId, servletRequest));
    }

    @PatchMapping("/{orderId}")
    @Override
    public Response<OrderResponseDto> updateOrder(@PathVariable UUID orderId, @RequestBody OrderUpdateRequestDto requestDto, HttpServletRequest servletRequest) {

        return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderService.updateOrder(orderId, requestDto, servletRequest));
    }

    @GetMapping
    @Override
    public Response<PageResponseDto<OrderListResponseDto>> getOrders(@ModelAttribute OrderSearchRequestDto requestDto, HttpServletRequest servletRequest) {
        return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderService.getOrders(requestDto, servletRequest));
    }


}
