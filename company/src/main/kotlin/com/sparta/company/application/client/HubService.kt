package com.sparta.company.application.client

import com.sparta.company.presentation.api.response.Response
import java.util.*


interface HubService {

    fun existHub(hubId: UUID): Response<Boolean>

}