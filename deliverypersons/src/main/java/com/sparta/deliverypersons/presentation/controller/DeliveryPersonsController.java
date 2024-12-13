package com.sparta.deliverypersons.presentation.controller;

import com.sparta.deliverypersons.application.service.DeliveryPersonsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliverypersons")
public class DeliveryPersonsController {

    private DeliveryPersonsService deliveryPersonsService;

}
