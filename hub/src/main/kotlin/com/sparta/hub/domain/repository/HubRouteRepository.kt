package com.sparta.hub.domain.repository

import com.sparta.hub.application.dto.response.HubRouteDetailResponseDto
import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.HubRoute
import java.util.*

interface HubRouteRepository {

    fun saveAll(hubRoutes: List<HubRoute>): List<HubRoute>

    fun deleteAll(hubRoutes: List<HubRoute>)

    fun findAll(): List<HubRoute>

    fun findByStartHubAndEndHub(startHub: Hub, endHub: Hub): HubRoute

    fun findByIds(ids: List<UUID>): List<HubRouteDetailResponseDto>

}