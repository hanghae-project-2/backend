package com.sparta.hub.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
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

}
