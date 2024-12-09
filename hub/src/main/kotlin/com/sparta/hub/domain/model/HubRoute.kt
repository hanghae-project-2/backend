package com.sparta.hub.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class HubRoute(
    endHub: Hub,
    startHub: Hub,
    estimatedSecond: Double?,
    estimatedMeter: Double?
) : BaseTimeEntity() {

    @Column(nullable = false)
    var estimatedSecond: Double? = estimatedSecond

    @Column(nullable = false)
    var estimatedMeter: Double? = estimatedMeter

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_hub_id", nullable = false)
    var endHub: Hub? = endHub

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_hub_id", nullable = false)
    var startHub: Hub? = startHub

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

}