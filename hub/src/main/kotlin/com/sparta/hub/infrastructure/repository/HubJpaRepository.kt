package com.sparta.hub.infrastructure.repository

import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.repository.HubRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

interface HubJpaRepository : JpaRepository<Hub, UUID>

@Repository
class HubRepositoryImpl(
    private val hubJpaRepository: HubJpaRepository
) : HubRepository {

    override fun save(hub: Hub): Hub {
        return hubJpaRepository.save(hub)
    }

    override fun findById(id: UUID): Optional<Hub> {
        return hubJpaRepository.findById(id)
    }

    override fun findAll(): List<Hub> {
        return hubJpaRepository.findAll()
    }

}
