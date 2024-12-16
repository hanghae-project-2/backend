package com.sparta.hub.libs

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@Constraint(validatedBy = [RoleValidator::class])
annotation class RoleValidation(
    vararg val roles: String,
    val message: String = "Invalid role",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
