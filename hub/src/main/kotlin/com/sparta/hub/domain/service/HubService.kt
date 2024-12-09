package com.sparta.hub.domain.service

import com.sparta.hub.application.dto.RouteResult
import java.util.*

interface HubService {

    fun registerHub(hubPosition: String, hubName: String): UUID

    fun navigateHubRoutes()

    fun getHubRoutes(startHubName: String, endHubName: String): RouteResult
}
