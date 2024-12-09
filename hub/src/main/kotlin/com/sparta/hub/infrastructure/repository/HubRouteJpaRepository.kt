package com.sparta.hub.infrastructure.repository

import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.HubRoute
import com.sparta.hub.domain.repository.HubRouteRepository
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface HubRouteJpaRepository : JpaRepository<HubRoute, UUID> {

    @EntityGraph(attributePaths = ["startHub", "endHub"])
    override fun findAll(): List<HubRoute>

    fun findByStartHubAndEndHub(startHub: Hub, endHub: Hub): HubRoute
}

@Repository
class HubRouteRepositoryImpl(
    private val hubRouteJpaRepository: HubRouteJpaRepository
) : HubRouteRepository {

    override fun saveAll(hubRoutes: List<HubRoute>): List<HubRoute> {
        return hubRouteJpaRepository.saveAll(hubRoutes)
    }

    override fun deleteAll(hubRoutes: List<HubRoute>) {
        hubRouteJpaRepository.deleteAll(hubRoutes)
    }

    override fun findAll(): List<HubRoute> {
        return hubRouteJpaRepository.findAll()
    }

    override fun findByStartHubAndEndHub(startHub: Hub, endHub: Hub): HubRoute {
        return hubRouteJpaRepository.findByStartHubAndEndHub(startHub, endHub)
    }

}
