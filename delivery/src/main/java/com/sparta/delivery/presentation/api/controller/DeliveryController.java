package com.sparta.delivery.presentation.api.controller;

import com.sparta.delivery.application.dto.DelieveryList;
import com.sparta.delivery.application.dto.DeliveryDetail;
import com.sparta.delivery.domain.model.DeliveryStatus;
import com.sparta.delivery.domain.service.DeliveryService;
import com.sparta.delivery.presentation.api.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/{deliveryId}")
    public Response<DeliveryDetail> getDeliveryById(@PathVariable UUID deliveryId){
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), deliveryService.getDeliveryById(deliveryId));
    }

    @PostMapping("")
    public Response<UUID> createDelivery(){
        return new Response(HttpStatus.CREATED.value(),HttpStatus.CREATED.getReasonPhrase(),deliveryService.createDelivery());
    }

    @PatchMapping("/{deliveryId}")
    public Response<UUID> updateDelivery(@PathVariable UUID deliveryId, @RequestBody DeliveryStatus status){
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),deliveryService.updateDelivery(deliveryId, status));
    }

    @DeleteMapping("/{deliveryId}")
    public Response<UUID> deleteDelivery(@PathVariable UUID deliveryId){
        return new Response(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.getReasonPhrase(), deliveryService.deleteDelivery(deliveryId));
    }

    @GetMapping("")
    public Response<List<DelieveryList>> getDeliveryList(){
        return new Response(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), deliveryService.getDeliveryList());
    }

}
