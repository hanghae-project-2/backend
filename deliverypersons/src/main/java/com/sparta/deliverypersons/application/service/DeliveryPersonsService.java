package com.sparta.deliverypersons.application.service;

import com.sparta.deliverypersons.application.event.DeliveryPersonCreatedEvent;
import com.sparta.deliverypersons.common.CustomException;
import com.sparta.deliverypersons.common.ErrorCode;
import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import com.sparta.deliverypersons.domain.model.DeliveryType;
import com.sparta.deliverypersons.infrastructure.messaging.producer.DeliveryEventProducer;
import com.sparta.deliverypersons.infrastructure.repository.DeliveryPersonsJpaRepository;
import com.sparta.deliverypersons.infrastructure.security.JwtUtil;
import com.sparta.deliverypersons.presentation.dto.request.CreateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.response.CreateDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeleteDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.UpdateDeliveryPersonResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPersonsService {

    private final DeliveryPersonsJpaRepository deliveryPersonsJpaRepository;
    private final DeliveryEventProducer deliveryEventProducer;
    private final JwtUtil jwtUtil;

    @Transactional
    public CreateDeliveryPersonResponse createDeliveryPerson(CreateDeliveryPersonRequest request) throws CustomException {
        UUID hubId = request.getHubId();
        UUID userId = request.getUserId();
        DeliveryType deliveryType;
        try {
            deliveryType = DeliveryType.valueOf(request.getDeliveryType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.INVALID_DELIVERY_TYPE);
        }

        if (deliveryType == DeliveryType.HUB_DELIVERY) {
            long hubDeliveryCount = deliveryPersonsJpaRepository.countByType(DeliveryType.HUB_DELIVERY);
            if (hubDeliveryCount >= 10) {
                throw new CustomException(ErrorCode.MAX_HUB_DELIVERY_PERSONS_REACHED);
            }
            hubId = null;
        } else if (deliveryType == DeliveryType.COMPANY_DELIVERY) {
            if (hubId == null) {
                throw new CustomException(ErrorCode.HUB_ID_REQUIRED);
            }
            long companyDeliveryCount = deliveryPersonsJpaRepository.countByTypeAndHubId(DeliveryType.COMPANY_DELIVERY, hubId);
            if (companyDeliveryCount >= 10) {
                throw new CustomException(ErrorCode.MAX_COMPANY_DELIVERY_PERSONS_REACHED);
            }
        }

        int newSequence = calculateNextSequence(deliveryType, hubId);

        DeliveryPersons deliveryPerson = DeliveryPersons.create(userId, hubId, deliveryType, newSequence);
        deliveryPersonsJpaRepository.save(deliveryPerson);

        DeliveryPersonCreatedEvent event = new DeliveryPersonCreatedEvent(
                deliveryPerson.getId()
        );
        deliveryEventProducer.sendDeliveryPersonCreatedEvent(event);

        return new CreateDeliveryPersonResponse(
                deliveryPerson.getId(),
                userId,
                "배송 담당자 등록이 완료 되었습니다."
        );
    }

    @Transactional
    public UpdateDeliveryPersonResponse updateDeliveryPerson(UUID id, UpdateDeliveryPersonRequest request, String jwt) {
        Claims claims = jwtUtil.parseToken(jwt);

        if (!jwtUtil.hasRequiredRole(claims, List.of("MASTER"))) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACTION);
        }

        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_PERSON_NOT_FOUND));

        DeliveryType deliveryType;
        try {
            deliveryType = DeliveryType.valueOf(request.getType());
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.INVALID_DELIVERY_TYPE);
        }

        if (deliveryType != DeliveryType.HUB_DELIVERY && request.getHubId() == null) {
            throw new CustomException(ErrorCode.HUB_ID_REQUIRED);
        }

        deliveryPerson.update(request.getHubId(), deliveryType, UUID.fromString(claims.getSubject()));

        return UpdateDeliveryPersonResponse.fromEntity(deliveryPerson.getHubId(), deliveryPerson.getType());
    }

    @Transactional(readOnly = true)
    public DeliveryPersonResponse getDeliveryPerson(UUID id) {
        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_PERSON_NOT_FOUND));

        return DeliveryPersonResponse.fromEntity(deliveryPerson);
    }

    @Transactional(readOnly = true)
    public Page<DeliveryPersonResponse> getAllDeliveryPersons(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return deliveryPersonsJpaRepository.findAll(pageable)
                .map(DeliveryPersonResponse::fromEntity);
    }

    @Transactional
    public DeleteDeliveryPersonResponse deleteDeliveryPerson(UUID id, String jwt) {
        Claims claims = jwtUtil.parseToken(jwt);

        if (!jwtUtil.hasRequiredRole(claims, List.of("COMPANY_ADMIN", "MASTER", "HUB_ADMIN", "DELIVERY_PERSON"))) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACTION);
        }

        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.DELIVERY_PERSON_NOT_FOUND));

        deliveryPerson.delete(UUID.fromString(claims.getSubject()));

        return DeleteDeliveryPersonResponse.of(deliveryPerson.getId(), deliveryPerson.getUserId());
    }

    private int calculateNextSequence(DeliveryType type, UUID hubId) {
        return deliveryPersonsJpaRepository.findMaxSequenceByTypeAndHubId(type, hubId)
                .orElse(0) + 1;
    }

    public UUID getRandomDeliveryPersonId() {
        byte[] binaryId = deliveryPersonsJpaRepository.findRandomDeliveryPersonId()
                .orElseThrow(() -> new IllegalStateException("No delivery person found"));

        return convertToUUID(binaryId);
    }

    public UUID convertToUUID(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

}
