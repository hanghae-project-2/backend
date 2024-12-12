package com.sparta.deliveryroute.application.service;

import com.sparta.deliveryroute.domain.repository.DeliveryRouteRepository;
import com.sparta.deliveryroute.domain.service.DeliveryRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRouteServiceImpl implements DeliveryRouteService {

	private final DeliveryRouteRepository deliverRouteRepository;
}
