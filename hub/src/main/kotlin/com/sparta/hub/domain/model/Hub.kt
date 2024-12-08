package com.sparta.hub.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull
import java.util.*

@Entity
class Hub(
    name: String,
    address: String,
    latitude: Double?,
    longitude: Double?,
) : BaseTimeEntity() {

    @NotNull
    var longitude: Double? = longitude

    @NotNull
    var latitude: Double? = latitude

    @NotNull
    var address: String = address

    @NotNull
    var name: String = name

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

}
