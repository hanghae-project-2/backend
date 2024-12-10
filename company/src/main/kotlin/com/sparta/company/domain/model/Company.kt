package com.sparta.company.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.*

@Entity
class Company(
    @Column(nullable = false)
    var name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: CompanyType,

    @Column(nullable = false)
    var hubId: UUID,

    @Column(nullable = false)
    var address: String,
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

}