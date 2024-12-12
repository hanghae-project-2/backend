package com.sparta.deliveryroute.presentation.api.controller;

import com.sparta.deliveryroute.application.service.DeliveryRouteService;
import com.sparta.deliveryroute.presentation.api.controller.docs.DeliveryRouteControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery-route")
public class DeliveryRouteController implements DeliveryRouteControllerDocs {

	private final DeliveryRouteService deliveryRouteService;

}
