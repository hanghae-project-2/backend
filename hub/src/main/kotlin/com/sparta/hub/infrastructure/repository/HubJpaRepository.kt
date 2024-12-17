package com.sparta.hub.infrastructure.repository

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.Projections
import com.sparta.hub.application.dto.request.HubSearchRequestDto
import com.sparta.hub.application.dto.response.HubSummaryResponseDto
import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.QHub.hub
import com.sparta.hub.domain.repository.HubRepository
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*

interface HubJpaRepository : JpaRepository<Hub, UUID> {

    fun findByNameIs(name: String): Optional<Hub>

    fun findByManager(manager: UUID): Hub?
}

@Repository
class HubRepositoryImpl(
    private val hubJpaRepository: HubJpaRepository
) : HubRepository, QuerydslRepositorySupport(Hub::class.java) {

    override fun save(hub: Hub): Hub {
        return hubJpaRepository.save(hub)
    }

    override fun findByNameIs(name: String): Optional<Hub> {
        return hubJpaRepository.findByNameIs(name)
    }

    override fun findAll(): List<Hub> {
        return hubJpaRepository.findAll()
    }

    override fun findByIdOrNull(id: UUID): Hub? {
        return hubJpaRepository.findById(id).orElse(null)
    }

    override fun existsById(id: UUID): Boolean {
        return hubJpaRepository.existsById(id)
    }

    override fun findByManager(manager: UUID): Hub? {
        return hubJpaRepository.findByManager(manager)
    }

    override fun findByIds(ids: List<UUID>): List<Hub> {
        return hubJpaRepository.findAllById(ids)
    }

    override fun findPageBy(
        pageRequest: Pageable,
        searchRequestDto: HubSearchRequestDto
    ): PageImpl<HubSummaryResponseDto> {

        val orders: MutableList<OrderSpecifier<*>> = ArrayList()

        pageRequest.sort.forEach {
            when (it.property) {
                "name" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        hub.name
                    )
                )

                "address" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        hub.address
                    )
                )

                "createdBy" -> orders.add(
                    OrderSpecifier(
                        if (it.isAscending) Order.ASC else Order.DESC,
                        hub.createdBy
                    )
                )
            }
        }

        val result = from(hub)
            .select(
                Projections.constructor(
                    HubSummaryResponseDto::class.java,
                    hub.id,
                    hub.name,
                    hub.address
                )
            )
            .where(
                searchRequestDto.name?.let { hub.name.contains(it) },
                searchRequestDto.address?.let { hub.address.contains(it) },
                searchRequestDto.createdBy?.let { hub.createdBy.eq(it) },
            )
            .orderBy(
                *orders.toTypedArray()
            )
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetchResults()

        return PageImpl(result.results, pageRequest, result.total)
    }
}
