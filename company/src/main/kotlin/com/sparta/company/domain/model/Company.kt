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
    name: String,
    type: CompanyType,
    address: String,
    hubId: UUID
) : BaseEntity() {

    @Column(nullable = false)
    var name: String? = name
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: CompanyType? = type

    @Column(nullable = false)
    var hubId: UUID? = hubId

    @Column(nullable = false)
    var address: String? = address
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

}