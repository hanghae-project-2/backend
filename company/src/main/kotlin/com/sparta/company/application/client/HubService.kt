package com.sparta.company.application.client

import java.util.*


interface HubService {

    fun existHub(hubId: UUID): Boolean

}