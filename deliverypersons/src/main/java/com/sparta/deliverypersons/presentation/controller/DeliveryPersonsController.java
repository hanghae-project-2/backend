package com.sparta.deliverypersons.presentation.controller;

import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.response.DeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.response.Response;
import org.springframework.data.domain.Page;
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
    @GetMapping("/{id}")
    public Response<DeliveryPersonResponse> getDeliveryPerson(
            @PathVariable UUID id) {

        DeliveryPersonResponse response = deliveryPersonsService.getDeliveryPerson(id);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }
    // 배송 담당자 조회(다건) api
    @GetMapping
    public Response<Page<DeliveryPersonResponse>> getAllDeliveryPerson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        // 서비스 호출
        Page<DeliveryPersonResponse> response = deliveryPersonsService.getAllDeliveryPersons(page, size);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }


    // 배송 담당자 삭제 api
}
