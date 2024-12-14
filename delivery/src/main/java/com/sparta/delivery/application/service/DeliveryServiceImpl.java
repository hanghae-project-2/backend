package com.sparta.delivery.application.service;

import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.*;
import com.sparta.delivery.domain.exception.DeliveryException;
import com.sparta.delivery.domain.exception.Error;
import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.model.DeliveryStatus;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import com.sparta.delivery.domain.service.DeliveryService;
import com.sparta.delivery.infrastructure.client.DeliveryRouteClient;
import com.sparta.delivery.infrastructure.client.HubClient;
import com.sparta.delivery.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final HubClient hubClient;
    private final DeliveryRouteClient deliveryRouteClient;
    private final UserClient userClient;

    @Override
    @Transactional(readOnly = true)
    public DeliveryDetailResponseDto getDeliveryById(UUID deliveryId) {

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        HubResponseDto originHub = hubClient.findHubById(delivery.getOriginHubId());
        HubResponseDto destinationHub = hubClient.findHubById(delivery.getDestinationHubId());

        List<DeliveryRouteResponseDto> deliveryRoutes = deliveryRouteClient.getDeliveryRoutesByDeliveryId(deliveryId);

        return DeliveryDetailResponseDto.from(delivery, originHub, destinationHub, deliveryRoutes);
    }

    @Override
    public UUID createDelivery() {
        return null;
    }

    @Override
    @Transactional
    public UUID updateDelivery(UUID deliveryId, DeliveryStatus status) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        delivery.updateDelivery(status);

        return deliveryId;
    }

    @Override
    @Transactional
    public UUID deleteDelivery(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        delivery.deleteDelivery(delivery.getCreatedBy());

        deliveryRouteClient.deleteByDeliveryId(deliveryId);

        return deliveryId;
    }

    @Transactional(readOnly = true)
    @Override
    public PageResponseDto<DeliveryListResponseDto> getDeliveries(DeliverySearchRequestDto requestDto) {
        Page<Delivery> deliveries = findDeliveries(requestDto);

        List<UUID> originHubIds = deliveries.map(Delivery::getOriginHubId).stream().distinct().toList();
        List<HubResponseDto> originHubs = hubClient.findHubsByIds(originHubIds);

        List<UUID> destinationHubIds = deliveries.map(Delivery::getDestinationHubId).stream().distinct().toList();
        List<HubResponseDto> destinationHubs = hubClient.findHubsByIds(destinationHubIds);

        Map<UUID, HubResponseDto> originHubMap = originHubs.stream().collect(Collectors.toMap(HubResponseDto::hubId, c -> c));
        Map<UUID, HubResponseDto> destinationHubMap = destinationHubs.stream().collect(Collectors.toMap(HubResponseDto::hubId, c -> c));

        Page<DeliveryListResponseDto> results = deliveries.map(delivery -> {
            HubResponseDto originHub = originHubMap.get(delivery.getOriginHubId());
            HubResponseDto destinationHub = destinationHubMap.get(delivery.getDestinationHubId());
            return DeliveryListResponseDto.from(delivery, originHub, destinationHub);
        });

        return PageResponseDto.of(results);
    }



    @Transactional(readOnly = true)
    @Override
    public UUID getDeliveryByOrderId(UUID orderId) {
        Delivery delivery = deliveryRepository.findByOrderIdAndIsDeleteFalse(orderId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );
        return delivery.getId();
    }

    @Transactional
    @Override
    public UUID deleteDeliveryByOrderId(UUID orderId) {
        Delivery delivery = deliveryRepository.findByOrderIdAndIsDeleteFalse(orderId).orElseThrow(
                () -> new DeliveryException(Error.NOT_FOUND_DELIVERY)
        );

        //TODO : USERID 받으면 수정
        delivery.deleteDelivery(orderId);

        deliveryRouteClient.deleteByDeliveryId(delivery.getId());

        return delivery.getId();
    }

    private Page<Delivery> findDeliveries(DeliverySearchRequestDto requestDto) {
        if ("DESTINATION_HUB_NAME".equals(requestDto.searchType()) || "ORIGIN_HUB_NAME".equals(requestDto.searchType())) {

            List<HubResponseDto> findhubs = hubClient.findHubsByName(requestDto.searchValue());
            List<UUID> hubIds = findhubs.stream().map(HubResponseDto::hubId).toList();

            return deliveryRepository.searchDeliveriesByHubIds(requestDto, hubIds);
        }
        return deliveryRepository.searchDeliveries(requestDto);
    }

}
