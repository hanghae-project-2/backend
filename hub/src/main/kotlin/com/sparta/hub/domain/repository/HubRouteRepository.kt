package com.sparta.hub.domain.repository

import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.HubRoute

interface HubRouteRepository {

    fun saveAll(hubRoutes: List<HubRoute>): List<HubRoute>

    fun deleteAll(hubRoutes: List<HubRoute>)

    fun findAll(): List<HubRoute>

    fun findByStartHubAndEndHub(startHub: Hub, endHub: Hub): HubRoute
    
}