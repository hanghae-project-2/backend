package com.sparta.hub.domain.exception

open class HubException(val error: Error) : RuntimeException()

class NotFoundHubException : HubException(Error.NOT_FOUND_HUB)