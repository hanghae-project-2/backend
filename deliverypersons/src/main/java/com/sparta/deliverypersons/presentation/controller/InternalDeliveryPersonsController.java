package com.sparta.deliverypersons.presentation.controller;

import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deliverypersons")
public class InternalDeliveryPersonsController {

    private final DeliveryPersonsService deliveryPersonsService;

    @GetMapping("/feign")
    public UUID getDeliveryPersonFeignClient(){
        return deliveryPersonsService.getRandomDeliveryPersonId();
    }
}
