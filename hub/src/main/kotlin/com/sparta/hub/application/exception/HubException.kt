package com.sparta.hub.application.exception

open class HubException(val error: Error) : RuntimeException()

class NotFoundHubException : HubException(Error.NOT_FOUND_HUB)

class UnableCalculateRouteException : HubException(Error.UNABLE_CALCULATE_ROUTE)

class AccessDeniedException : HubException(Error.ACCESS_DENIED)