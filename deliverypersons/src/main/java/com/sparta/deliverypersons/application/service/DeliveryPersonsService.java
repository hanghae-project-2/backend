package com.sparta.deliverypersons.application.service;

import com.sparta.deliverypersons.domain.model.DeliveryPersons;
import com.sparta.deliverypersons.infrastructure.messaging.producer.DeliveryEventProducer;
import com.sparta.deliverypersons.infrastructure.repository.DeliveryPersonsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryPersonsService {

    private final DeliveryPersonsJpaRepository deliveryPersonsJpaRepository;
    private final DeliveryEventProducer deliveryEventProducer;

    @Transactional
    public DeliveryPersons createDeliveryPerson(UUID deliveryId) {
        int maxSequence = deliveryPersonsJpaRepository.findMaxSequence();
        int newSequence = 0;

        // creatDelivery 이벤트에서 어떤 인자를 수신하는지 확인 필요..



        // 어떤 기준으로 허브, 업체 담당을 배정해줄건지?
        // 또한 어떤 기준으로 해당 유저를 배정할건지?
        // 1. 해당 유저의 담당 권한이 이미 배정되어 있으면 그거에 따라서 가까운 허브 혹은 업체로 배정하기
        // 2. 해당 유저의 담당 권한이 아직 없다면 어떠한 기준에 따라 담당 권한(HUB, COMPANY) 배정하기



        // 시퀀스를 체크해서 각 업체, 허브 담당자 별로 최대 10번까지만 넣어주는 로직 필요..



        // 현재 위치 기반 가까운 사람으로 담당 허브 ID 배정해주는? 로직 필요



        // 배달 담당자 생성하고 db 저장하기
        DeliveryPersons deliveryPerson = DeliveryPersons.create(userId, hubId, type, newSequence);
        return deliveryPersonsJpaRepository.save(deliveryPerson);



        // 배달 담당자 생성시 해당 유저 role 바꿔주고 저장하는 api 호출 필요?



        // 배달담당자 생성 완료시 이벤트 발행해야함?
        deliveryEventProducer.sendDeliveryEvent(..);

    }

    public boolean isValidHub(UUID hubId) {
        // 허브 ID 유효성 검증 로직 추가해야함
        return true; // 임시로 항상 true 반환
    }

}
