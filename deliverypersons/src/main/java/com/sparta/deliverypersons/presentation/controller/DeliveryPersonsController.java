package com.sparta.deliverypersons.presentation.controller;

import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.response.CreateDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeleteDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.UpdateDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/deliverypersons")
@RequiredArgsConstructor
public class DeliveryPersonsController {

    private final DeliveryPersonsService deliveryPersonsService;

    @PostMapping
    public Response<CreateDeliveryPersonResponse> createDeliveryPerson(
            @RequestParam UUID userId,
            @RequestParam(required = false) UUID hubId,
            @RequestParam String deliveryType) {

        CreateDeliveryPersonResponse response = deliveryPersonsService.createDeliveryPerson(userId, hubId, deliveryType);

        return new Response<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                response
        );
    }

    @PatchMapping("/{id}")
    public Response<UpdateDeliveryPersonResponse> updateDeliveryPerson(
            @PathVariable UUID id,
            @RequestBody UpdateDeliveryPersonRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.replace("Bearer ", "");

        UpdateDeliveryPersonResponse response = deliveryPersonsService.updateDeliveryPerson(id, request, jwt);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }

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

    @GetMapping
    public Response<Page<DeliveryPersonResponse>> getAllDeliveryPerson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<DeliveryPersonResponse> response = deliveryPersonsService.getAllDeliveryPersons(page, size);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }

    @DeleteMapping("/{id}")
    public Response<DeleteDeliveryPersonResponse> deleteDeliveryPerson(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.replace("Bearer ", "");

        DeleteDeliveryPersonResponse response = deliveryPersonsService.deleteDeliveryPerson(id, jwt);

        return new Response<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                response
        );
    }
}
