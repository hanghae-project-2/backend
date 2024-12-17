package com.sparta.delivery.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.delivery.application.dto.request.DeliverySearchRequestDto;
import com.sparta.delivery.application.dto.response.DeliveryListResponseDto;
import com.sparta.delivery.domain.model.Delivery;
import com.sparta.delivery.domain.repository.DeliveryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static com.sparta.delivery.domain.model.QDelivery.delivery;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    private final DeliveryJpaRepository deliveryJpaRepository;
    private final JPAQueryFactory queryFactory;

    public DeliveryRepositoryImpl(DeliveryJpaRepository deliveryJpaRepository, EntityManager entityManager) {
        this.deliveryJpaRepository = deliveryJpaRepository;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Delivery> findById(UUID deliveryId) {
        return deliveryJpaRepository.findById(deliveryId);
    }

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryJpaRepository.save(delivery);
    }

    @Override
    public Page<DeliveryListResponseDto> getDeliveries(Pageable pageable, DeliverySearchRequestDto requestDto) {
        return null;
    }

    @Override
    public Optional<Delivery> findByOrderIdAndIsDeleteFalse(UUID orderId) {
        return deliveryJpaRepository.findByOrderIdAndIsDeleteFalse(orderId);
    }

    @Override
    public Page<Delivery> searchDeliveriesByHubIds(DeliverySearchRequestDto requestDto, List<UUID> hubIds) {
        int skip = (requestDto.page() - 1) * requestDto.limit();

        List<Delivery> results = queryFactory.selectFrom(delivery)
                .where(filterDeliveriesByHubIds(requestDto, hubIds)
                        .and(delivery.isDelete.isFalse()))
                .orderBy(getOrderSpecifier(requestDto))
                .offset(skip)
                .limit(requestDto.limit())
                .fetch();

        long totalCount = getDeliveriesByHubIdsTotalCount(requestDto, hubIds);

        return new PageImpl<>(results, requestDto.getPageable(), totalCount);

    }

    @Override
    public Page<Delivery> searchDeliveries(DeliverySearchRequestDto requestDto) {
        int skip = (requestDto.page() - 1) * requestDto.limit();

        List<Delivery> results = queryFactory.selectFrom(delivery)
                .where(filterDeliveries(requestDto)
                        .and(delivery.isDelete.isFalse()))
                .orderBy(getOrderSpecifier(requestDto))
                .offset(skip)
                .limit(requestDto.limit())
                .fetch();

        long totalCount = getDeliveriesTotalCount(requestDto);

        return new PageImpl<>(results, requestDto.getPageable(), totalCount);
    }

    private BooleanBuilder filterDeliveries(DeliverySearchRequestDto requestDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.hasText(requestDto.searchValue())) {
            booleanBuilder.and(delivery.id.eq(UUID.fromString(requestDto.searchValue())));
        }
        return booleanBuilder;
    }

    private OrderSpecifier<?> getOrderSpecifier(DeliverySearchRequestDto requestDto) {
        PathBuilder<Delivery> orderByExpression = new PathBuilder<>(Delivery.class, requestDto.orderBy());
        com.querydsl.core.types.Order direction = requestDto.sort().equals(Sort.Direction.DESC) ? com.querydsl.core.types.Order.DESC : com.querydsl.core.types.Order.ASC;
        return new OrderSpecifier(direction, orderByExpression);
    }

    private long getDeliveriesByHubIdsTotalCount(DeliverySearchRequestDto requestDto, List<UUID> hubIds) {
        return queryFactory.select(Wildcard.count)
                .from(delivery)
                .where(filterDeliveriesByHubIds(requestDto, hubIds )
                        .and(delivery.isDelete.isFalse()))
                .fetch().get(0);
    }

    private BooleanBuilder filterDeliveriesByHubIds(DeliverySearchRequestDto requestDto, List<UUID> hubIds) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if ("ORIGIN_HUB_NAME".equals(requestDto.searchType())) {
            booleanBuilder.and(delivery.startHubId.in(hubIds));
        }else if ("DESTINATION_HUB_NAME".equals(requestDto.searchType())) {
            booleanBuilder.and(delivery.endHubId.in(hubIds));
        }
        return booleanBuilder;
    }

    private long getDeliveriesTotalCount(DeliverySearchRequestDto requestDto) {
        return queryFactory.select(Wildcard.count)
                .from(delivery)
                .where(filterDeliveries(requestDto)
                        .and(delivery.isDelete.isFalse()))
                .fetch().get(0);
    }


}
