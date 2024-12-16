package com.sparta.deliverypersons.presentation.controller;

import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/deliverypersons")
public class DeliveryPersonsController {

    private DeliveryPersonsService deliveryPersonsService;


    // 배송 담당자 수정 api
    @PatchMapping("/{id}")
    public Response<?> updateDeliveryPerson(
            @PathVariable UUID id,
            @RequestBody UpdateDeliveryPersonRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {

        // JWT 토큰 추출
        String jwt = authorizationHeader.replace("Bearer ", "");

        // 나중에 Response에 담아주고 바디에 응답해야함
        deliveryPersonsService.updateDeliveryPerson(id, request, jwt);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                null
        );
    }


    // 배송 담당자 조회(단건) api
    @PatchMapping("/{id}")
    public Response<?> getDeliveryPerson(
            @PathVariable UUID id,
            @RequestBody UpdateDeliveryPersonRequest request) {

        // 서비스 호출하고 응답 객체에 담아줘야함.

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                null
        );
    }
    // 배송 담당자 조회(다건) api

    // 배송 담당자 삭제 api
}
