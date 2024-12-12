package com.sparta.company.domain.client

import com.sparta.company.presentation.api.response.Response
import java.util.*


interface HubClient {

    fun existHub(hubId: UUID): Response<Boolean>

}