package com.sparta.company.domain.exception

open class CompanyException(val error: Error) : RuntimeException()

class NotFoundCompanyException : CompanyException(Error.NOT_FOUND_COMPANY)