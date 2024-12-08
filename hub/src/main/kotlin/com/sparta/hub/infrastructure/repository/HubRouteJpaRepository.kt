package com.sparta.hub.infrastructure.repository

import com.sparta.hub.domain.model.HubRoute
import com.sparta.hub.domain.repository.HubRouteRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface HubRouteJpaRepository : JpaRepository<HubRoute, UUID>

@Repository
class HubRouteRepositoryImpl(
    private val hubRouteJpaRepository: HubRouteJpaRepository
) : HubRouteRepository {

    override fun saveAll(hubRoutes: List<HubRoute>): List<HubRoute> {
        return hubRouteJpaRepository.saveAll(hubRoutes)
    }

}
