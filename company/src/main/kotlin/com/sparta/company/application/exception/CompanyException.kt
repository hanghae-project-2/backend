package com.sparta.company.application.exception

open class CompanyException(val error: Error) : RuntimeException()

class NotFoundCompanyException : CompanyException(Error.NOT_FOUND_COMPANY)

class IncorrectCompanyTypeException : CompanyException(Error.INCORRECT_COMPANY_TYPE)

class IncorrectHubIdException : CompanyException(Error.INCORRECT_HUB_ID)

class AccessDeniedException : CompanyException(Error.ACCESS_DENIED)