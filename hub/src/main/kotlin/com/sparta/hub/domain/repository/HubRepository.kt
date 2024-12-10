package com.sparta.hub.domain.repository

import com.sparta.hub.domain.model.Hub
import java.util.*

interface HubRepository {

    fun save(hub: Hub): Hub

    fun findByNameIs(name: String): Optional<Hub>

    fun findAll(): List<Hub>

    fun findById(id: UUID): Optional<Hub>

    fun existsById(id: UUID): Boolean

}
