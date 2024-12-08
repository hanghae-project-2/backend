package com.sparta.hub.domain.service

import java.util.*

interface HubService {

    fun registerHub(hubPosition: String, hubName: String): UUID

    fun navigateHubRoutes()
}
