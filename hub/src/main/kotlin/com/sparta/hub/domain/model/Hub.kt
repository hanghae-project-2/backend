package com.sparta.hub.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.DynamicUpdate
import java.util.*

@Entity
@DynamicUpdate
class Hub(
    name: String,

    address: String,

    latitude: Double,

    longitude: Double,
) : BaseEntity() {

    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var address: String = address

    @Column(nullable = false)
    var latitude: Double? = latitude
        protected set

    @Column(nullable = false)
    var longitude: Double? = longitude
        protected set

    var manager: UUID? = null
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    constructor(
        name: String,
        address: String,
        latitude: Double,
        longitude: Double,
        createdBy: String
    ) : this(name, address, latitude, longitude) {
        super.createdBy(UUID.fromString(createdBy))
    }

    fun checkManager(manager: UUID): Boolean {
        return this.manager == manager
    }

    fun updateInfo(latitude: Double?, longitude: Double?, name: String, manager: String, updatedBy: String) {
        this.latitude = latitude
        this.longitude = longitude
        this.name = name
        this.manager = UUID.fromString(manager)
        super.updatedBy(UUID.fromString(updatedBy))
    }

    fun markAsDelete(deletedBy: String) {
        super.deletedBy(UUID.fromString(deletedBy))
    }
}
