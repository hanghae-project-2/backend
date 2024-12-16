package com.sparta.company.libs

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class RoleValidator : ConstraintValidator<RoleValidation, HttpServletRequest> {

    private lateinit var roles: Set<String>

    override fun initialize(constraintAnnotation: RoleValidation) {
        roles = constraintAnnotation.roles.toSet()
    }

    override fun isValid(request: HttpServletRequest?, context: ConstraintValidatorContext?): Boolean {

        val role = request?.getHeader("X-Authenticated-User-Role")

        return role != null && (roles.contains("ANY_ROLE") || roles.contains(role))
    }

}