package com.sparta.hub.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime
import java.util.*

@Entity
class HubRoute(
    endHub: Hub,
    startHub: Hub,
    estimatedSecond: Int,
    estimatedMeter: Int
) : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_hub_id", nullable = false)
    var startHub: Hub? = startHub

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_hub_id", nullable = false)
    var endHub: Hub? = endHub

    @Column(nullable = false)
    var estimatedSecond: Int = estimatedSecond
        protected set

    @Column(nullable = false)
    var estimatedMeter: Int = estimatedMeter
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    fun updateEstimatedInfo(
        estimatedSecond: Int,
        estimatedMeter: Int,
        updatedBy: String
    ) {
        this.estimatedSecond = estimatedSecond
        this.estimatedMeter = estimatedMeter
        this.updatedBy = UUID.fromString(updatedBy)
        this.updatedAt = LocalDateTime.now()
    }
}