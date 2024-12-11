package com.sparta.hub.presentation.api.request

import com.sparta.hub.application.dto.request.HubSearchRequestDto
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

data class HubSearchRequest(
    @RequestParam
    val name: String?,
    @RequestParam
    val address: String?,
    @RequestParam
    val createdBy: UUID?,
)

fun HubSearchRequest.toDto() = HubSearchRequestDto(
    name = name,
    address = address,
    createdBy = createdBy
)