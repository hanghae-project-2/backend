package com.sparta.company.domain.client

import java.util.*


interface HubClient {

    fun existHub(hubId: UUID): Boolean

}