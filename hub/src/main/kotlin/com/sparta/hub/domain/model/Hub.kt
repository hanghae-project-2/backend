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
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var address: String,

    @Column(nullable = false)
    var latitude: Double? = null,

    @Column(nullable = false)
    var longitude: Double? = null,
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    fun updatePosition(latitude: Double?, longitude: Double?) {
        this.latitude = latitude
        this.longitude = longitude
    }

    fun updateName(name: String) {
        this.name = name
    }

    fun markAsDelete() {
        this.isDelete = true
        this.isPublic = false
    }
}
