package com.sparta.hub.presentation.api.request

import com.sparta.hub.application.dto.request.HubSearchRequestDto
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

data class HubSearchRequest(
    @RequestParam(required = false)
    val name: String?,
    @RequestParam(required = false)
    val address: String?,
    @RequestParam(required = false)
    val createdBy: UUID?,
)

fun HubSearchRequest.toDto() = HubSearchRequestDto(
    name = name,
    address = address,
    createdBy = createdBy
)