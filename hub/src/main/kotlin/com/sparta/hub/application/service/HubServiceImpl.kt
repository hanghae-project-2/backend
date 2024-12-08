package com.sparta.hub.application.service

import com.fasterxml.jackson.databind.JsonNode
import com.sparta.hub.domain.exception.NotFoundHubException
import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.HubRoute
import com.sparta.hub.domain.repository.HubRepository
import com.sparta.hub.domain.repository.HubRouteRepository
import com.sparta.hub.domain.service.HubService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
@Transactional
class HubServiceImpl(
    private val hubRouteRepository: HubRouteRepository,
    private val hubRepository: HubRepository,
    private val webClient: WebClient
) : HubService {

    @Value("\${geocoder.api.key}")
    private lateinit var geocoderApiKey: String

    @Value("\${kakao.api.key}")
    private lateinit var kakaoApiKey: String

    override fun registerHub(hubPosition: String, hubName: String): UUID {

        val result = webClient
            .get()
            .uri("https://maps.googleapis.com/maps/api/geocode/json?address=$hubPosition&key=$geocoderApiKey")
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map { response ->

                println("Full Response: ${response.toPrettyString()}")

                val results = response.get("results")

                val firstResult = results.get(0)
                val geometry = firstResult?.get("geometry")
                val location = geometry?.get("location")

                mapOf(
                    "latitude" to location?.get("lat")?.asDouble(),
                    "longitude" to location?.get("lng")?.asDouble()
                )
            }
            .onErrorResume { ex ->
                Mono.error(ex)
            }
            .block()

        return hubRepository.save(
            Hub(
                name = hubName,
                address = hubPosition,
                latitude = result?.get("latitude"),
                longitude = result?.get("longitude"),
            )
        ).id!!
    }

    override fun navigateHubRoutes() {

        val hubs = hubRepository.findAll()
        val hubMap = hubs.associateBy { it.name }

        val connections = mapOf(
            "경기 남부 센터" to listOf("경기 북부 센터", "서울특별시 센터", "인천광역시 센터", "강원특별자치도 센터", "경상북도 센터", "대전광역시 센터", "대구광역시 센터"),
            "대전광역시 센터" to listOf(
                "충청남도 센터",
                "충청북도 센터",
                "세종특별자치시 센터",
                "전북특별자치도 센터",
                "광주광역시 센터",
                "전라남도 센터",
                "대구광역시 센터"
            ),
            "대구광역시 센터" to listOf("경상남도 센터", "부산광역시 센터", "울산광역시 센터", "경상북도 센터"),
        )

        val hubRouteList: List<HubRoute> = connections.flatMap { (startHubName, connectedHubs) ->
            connectedHubs.flatMap { endHubName ->
                val startHub = hubMap[startHubName] ?: throw NotFoundHubException()
                val endHub = hubMap[endHubName] ?: throw NotFoundHubException()

                val forwardResult = calculateForHubRoute(startHub, endHub)
                val reverseResult = calculateForHubRoute(endHub, startHub)

                listOfNotNull(forwardResult, reverseResult).distinct()
            }

        }

        hubRouteRepository.saveAll(hubRouteList)
    }

    private fun calculateForHubRoute(startHub: Hub, endHub: Hub): HubRoute? {
        return webClient
            .get()
            .uri("https://apis-navi.kakaomobility.com/v1/directions?origin=${startHub.longitude},${startHub.latitude}&destination=${endHub.longitude},${endHub.latitude}")
            .header("Authorization", "KakaoAK $kakaoApiKey")
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map { response ->
                val routes = response.get("routes")
                val firstRoute = routes.get(0)
                val summary = firstRoute.get("summary")

                HubRoute(
                    estimatedSecond = summary.get("duration")?.asDouble(),
                    estimatedMeter = summary.get("distance")?.asDouble(),
                    startHubId = startHub,
                    endHubId = endHub
                )
            }
            .block()
    }

}
