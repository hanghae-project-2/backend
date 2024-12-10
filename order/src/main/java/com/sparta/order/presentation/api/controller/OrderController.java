package com.sparta.order.presentation.api.controller;

import com.sparta.order.application.dto.OrderCreate;
import com.sparta.order.application.dto.OrderDetail;
import com.sparta.order.application.dto.OrderResponse;
import com.sparta.order.domain.service.OrderService;
import com.sparta.order.presentation.api.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public Response<OrderResponse> createOrder(@RequestBody OrderCreate request){
        return new Response(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), orderService.createOrder(request));
    }

    @DeleteMapping("/{orderId}")
    public Response<OrderResponse> deleteOrder(@PathVariable UUID orderId){
        return new Response<>(HttpStatus.NO_CONTENT.value(),HttpStatus.NO_CONTENT.getReasonPhrase(), orderService.deleteOrder(orderId));
    }

    @GetMapping("/{orderId}")
    public Response<OrderDetail> getOrderById(@PathVariable UUID orderId){
        return new Response<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderService.getOrderById(orderId));
    }
}
