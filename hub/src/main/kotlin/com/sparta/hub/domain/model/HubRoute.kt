package com.sparta.hub.domain.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.*

@Entity
class HubRoute(
    endHubId: Hub,
    startHubId: Hub,
    estimatedSecond: Double?,
    estimatedMeter: Double?
) : BaseTimeEntity() {

    val estimatedSecond: Double? = estimatedSecond

    val estimatedMeter: Double? = estimatedMeter

    @ManyToOne
    @JoinColumn(name = "end_hub_id")
    val endHubId: Hub? = endHubId

    @ManyToOne
    @JoinColumn(name = "start_hub_id")
    val startHubId: Hub? = startHubId

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

}