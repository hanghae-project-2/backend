package com.sparta.deliverypersons.application.service;

import com.sparta.deliverypersons.common.CustomException;
import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import com.sparta.deliverypersons.domain.model.DeliveryType;
import com.sparta.deliverypersons.infrastructure.messaging.producer.DeliveryEventProducer;
import com.sparta.deliverypersons.infrastructure.repository.DeliveryPersonsJpaRepository;
import com.sparta.deliverypersons.infrastructure.security.JwtUtil;
import com.sparta.deliverypersons.presentation.dto.request.UpdateDeliveryPersonRequest;
import com.sparta.deliverypersons.presentation.dto.response.DeleteDeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.DeliveryPersonResponse;
import com.sparta.deliverypersons.presentation.dto.response.UpdateDeliveryPersonResponse;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPersonsService {

    private final DeliveryPersonsJpaRepository deliveryPersonsJpaRepository;
    private final DeliveryEventProducer deliveryEventProducer;
    private final JwtUtil jwtUtil;

    @Transactional
    public DeliveryPersons createDeliveryPerson(UUID deliveryId) {
        int maxSequence = deliveryPersonsJpaRepository.findMaxSequence();
        int newSequence = 0;

        // creatDelivery 이벤트에서 어떤 인자를 수신하는지 확인 필요..



        // 어떤 기준으로 허브, 업체 담당을 배정해줄건지?
        // 또한 어떤 기준으로 해당 유저를 배정할건지?
        // 1. 해당 유저의 담당 권한(HUB, COMPANY)이 이미 배정되어 있으면 그거에 따라서 가까운 허브 혹은 업체로 배정하기
        // 2. 해당 유저의 담당 권한이 아직 없다면 어떠한 기준에 따라 담당 권한(HUB, COMPANY) 배정하기



        // 시퀀스를 체크해서 각 업체, 허브 담당자 별로 최대 10번까지만 넣어주는 로직 필요..



        // 현재 위치 기반 가까운 사람으로 담당 허브 ID 배정해주는? 로직 필요



        // 배달 담당자 생성하고 db 저장하기
        DeliveryPersons deliveryPerson = DeliveryPersons.create(userId, hubId, type, newSequence);
        return deliveryPersonsJpaRepository.save(deliveryPerson);



        // 배달 담당자 생성시 해당 유저 role 바꿔주고 저장하는 api 호출 필요?



        // 배달담당자 생성 완료시 이벤트 발행해야하나? 어떤걸 발행하나?
        deliveryEventProducer.sendDeliveryEvent(..);

    }

    public boolean isValidHub(UUID hubId) {
        // 허브 ID 유효성 검증 로직 추가해야함
        return true; // 임시로 항상 true 반환
    }

    @Transactional
    public UpdateDeliveryPersonResponse updateDeliveryPerson(UUID id, UpdateDeliveryPersonRequest request, String jwt) {
        Claims claims = jwtUtil.parseToken(jwt);

        if (!jwtUtil.hasRequiredRole(claims, List.of("MASTER"))) {
            throw new CustomException("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException("배송 담당자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        DeliveryType deliveryType;
        try {
            deliveryType = DeliveryType.valueOf(request.getType());
        } catch (IllegalArgumentException e) {
            throw new CustomException("잘못된 배송 타입입니다: " + request.getType(), HttpStatus.BAD_REQUEST);
        }

        if (deliveryType != DeliveryType.HUB_DELIVERY && request.getHubId() == null) {
            throw new CustomException("허브 ID는 필수입니다.", HttpStatus.BAD_REQUEST);
        }

        deliveryPerson.update(request.getHubId(), deliveryType, UUID.fromString(claims.getSubject()));

        return UpdateDeliveryPersonResponse.fromEntity(deliveryPerson.getHubId(), deliveryPerson.getType());
    }

    @Transactional(readOnly = true)
    public DeliveryPersonResponse getDeliveryPerson(UUID id) {

        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException("배송 담당자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

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
            throw new CustomException("삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }

        DeliveryPersons deliveryPerson = deliveryPersonsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException("배송 담당자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND));

        deliveryPerson.delete(UUID.fromString(claims.getSubject()));

        return DeleteDeliveryPersonResponse.of(deliveryPerson.getId(), deliveryPerson.getUserId());
    }
}
