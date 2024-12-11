package com.sparta.hub.domain.repository

import com.sparta.hub.application.dto.request.HubSearchRequestDto
import com.sparta.hub.application.dto.response.HubSummaryResponseDto
import com.sparta.hub.domain.model.Hub
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface HubRepository {

    fun save(hub: Hub): Hub

    fun findByNameIs(name: String): Optional<Hub>

    fun findAll(): List<Hub>

    fun findById(id: UUID): Optional<Hub>

    fun existsById(id: UUID): Boolean

    fun findPageBy(pageRequest: Pageable, searchRequestDto: HubSearchRequestDto): Page<HubSummaryResponseDto>

}
