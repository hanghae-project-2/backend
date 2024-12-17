package com.sparta.delivery.libs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class RoleValidator implements ConstraintValidator<RoleValidation, HttpServletRequest> {

    private Set<String> roles = new HashSet<>();

    @Override
    public void initialize(RoleValidation constraintAnnotation) {
        String[] definedRoles = constraintAnnotation.roles();
        if (definedRoles != null) {
            for (String role : definedRoles) {
                roles.add(role);
            }
        }
    }

    @Override
    public boolean isValid(HttpServletRequest request, ConstraintValidatorContext context) {
        if (request == null) {
            return false;
        }

        String role = request.getHeader("X-Authenticated-User-Role");
        return role != null && (roles.contains("ANY_ROLE") || roles.contains(role));
    }
}
