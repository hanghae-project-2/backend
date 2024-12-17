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
    var name: String = name
        protected set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: CompanyType = type
        protected set

    @Column(nullable = false)
    var hubId: UUID = hubId
        protected set

    @Column(nullable = false)
    var address: String = address
        protected set

    var manager: UUID? = UUID.randomUUID()
        protected set

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null

    constructor(
        name: String,
        type: CompanyType,
        address: String,
        hubId: UUID,
        createdBy: String,
    ) : this(name, type, address, hubId) {
        super.createdBy(UUID.fromString(createdBy))
    }

    fun checkManager(manager: String): Boolean {
        return this.manager == UUID.fromString(manager)
    }

    fun updateInfo(name: String, type: String, address: String, manager: String, updatedBy: String) {
        this.name = name
        this.type = CompanyType.valueOf(type)
        this.address = address
        this.manager = UUID.fromString(manager)
        super.updatedBy(UUID.fromString(updatedBy))
    }

    fun markAsDelete(deletedBy: String) {
        super.deletedBy(UUID.fromString(deletedBy))
    }

}