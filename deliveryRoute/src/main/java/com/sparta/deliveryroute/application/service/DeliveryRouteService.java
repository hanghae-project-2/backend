package com.sparta.deliveryroute.application.service;

import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {

	private final DeliveryRouteRepository deliverRouteRepository;

}
