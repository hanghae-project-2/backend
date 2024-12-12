package com.sparta.hub.application.service

import com.fasterxml.jackson.databind.JsonNode
import com.sparta.hub.application.dto.RouteInfo
import com.sparta.hub.application.dto.RouteResult
import com.sparta.hub.application.dto.RouteState
import com.sparta.hub.application.dto.request.HubRequestDto
import com.sparta.hub.application.dto.request.HubSearchRequestDto
import com.sparta.hub.application.dto.response.HubDetailResponseDto
import com.sparta.hub.application.dto.response.HubSummaryResponseDto
import com.sparta.hub.application.dto.response.toResponseDto
import com.sparta.hub.application.dto.toRouteInfo
import com.sparta.hub.application.redis.RedisService
import com.sparta.hub.domain.exception.NotFoundHubException
import com.sparta.hub.domain.exception.UnableCalculateRouteException
import com.sparta.hub.domain.model.Hub
import com.sparta.hub.domain.model.HubRoute
import com.sparta.hub.domain.repository.HubRepository
import com.sparta.hub.domain.repository.HubRouteRepository
import com.sparta.hub.domain.service.HubService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Service
class HubServiceImpl(
    private val webClient: WebClient,
    private val redisService: RedisService,
    private val hubRepository: HubRepository,
    private val hubRouteRepository: HubRouteRepository,
) : HubService {

    @Value("\${geocoder.api.key}")
    private lateinit var geocoderApiKey: String

    @Value("\${kakao.api.key}")
    private lateinit var kakaoApiKey: String

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

    @Transactional
    override fun registerHub(hubAddress: String, hubName: String): UUID {

        val result = findLatitudeAndLongitude(hubAddress)

        return hubRepository.save(
            Hub(
                name = hubName,
                address = hubAddress,
                latitude = result?.get("latitude"),
                longitude = result?.get("longitude"),
            )
        ).id!!
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    override fun navigateHubRoutes() {

        val hubRoutes = hubRouteRepository.findAll()
        val hubs = hubRoutes.mapNotNull { it.startHub }.distinct()

        val hubMap = hubs.associateBy { it.name }
        val hubRouteMap = hubRoutes.associateBy { "${it.startHub?.id}-${it.endHub?.id}" }

        val hubRouteList: List<HubRoute> = connections.flatMap { (startHubName, connectedHubs) ->
            connectedHubs.flatMap { endHubName ->
                val startHub = hubMap[startHubName] ?: throw NotFoundHubException()
                val endHub = hubMap[endHubName] ?: throw NotFoundHubException()

                val forwardResult = calculateForHubRoute(startHub, endHub)
                val reverseResult = calculateForHubRoute(endHub, startHub)

                val forwardHubRoute = hubRouteMap["${startHub.id}-${endHub.id}"].apply {
                    this?.estimatedMeter = forwardResult?.estimatedMeter
                    this?.estimatedSecond = forwardResult?.estimatedSecond
                }

                val reverseHubRoute = hubRouteMap["${endHub.id}-${startHub.id}"].apply {
                    this?.estimatedMeter = reverseResult?.estimatedMeter
                    this?.estimatedSecond = reverseResult?.estimatedSecond
                }

                listOfNotNull(forwardHubRoute, reverseHubRoute)
            }

        }.distinct()

        hubRouteRepository.saveAll(hubRouteList)
    }

    @Transactional(readOnly = true)
    override fun getOptimalHubRoutes(startHubName: String, endHubName: String): RouteResult {

        val startHub = hubRepository.findByNameIs(startHubName).orElseThrow { NotFoundHubException() }
        val endHub = hubRepository.findByNameIs(endHubName).orElseThrow { NotFoundHubException() }

        redisService.getHubRouteResult(startHubName, endHubName)?.let {
            return it
        }

        val createRouteInfoGraph = hubRouteRepository.findAll().groupBy({ it.startHub!!.name }) { it.toRouteInfo() }

        return findOptimalRoute(startHub, endHub, createRouteInfoGraph)
    }

    @Transactional(readOnly = true)
    override fun searchHubs(pageable: Pageable, requestDto: HubSearchRequestDto): Page<HubSummaryResponseDto> {

        return hubRepository.findPageBy(pageable, requestDto)
    }

    @Transactional(readOnly = true)
    override fun getHubDetail(hubId: UUID): HubDetailResponseDto {
        val hub = hubRepository.findById(hubId).orElseThrow { NotFoundHubException() }

        val routeResultList = redisService.getHubRouteByStartingWithKey(hub.name)

        return hub.toResponseDto(routeResultList)
    }

    @Transactional
    override fun modifyHub(hubId: UUID, hubRequestDto: HubRequestDto): UUID {
        val hub = hubRepository.findById(hubId).orElseThrow { NotFoundHubException() }

        val latitudeAndLongitude = findLatitudeAndLongitude(hubRequestDto.address)

        val latitude = latitudeAndLongitude?.get("latitude")
        val longitude = latitudeAndLongitude?.get("longitude")

        if (hubRequestDto.isDelete) {
            hub.markAsDelete()
        }
        hub.updatePosition(latitude, longitude)
        hub.updateName(hubRequestDto.name)

        return hubRepository.save(hub).id!!
    }

    @Transactional(readOnly = true)
    override fun existHub(hubId: UUID): Boolean {
        return hubRepository.existsById(hubId)
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    fun updateForOptimalHubRoutes() {

        val hubRoutes = hubRouteRepository.findAll()

        val createRouteInfoGraph = hubRoutes.groupBy({ it.startHub!!.name }) { it.toRouteInfo() }

        val hubs = hubRoutes.mapNotNull { it.startHub }.distinct()
        val reverseList = hubs.asReversed()

        hubs.forEach { hub ->
            reverseList.forEach { reverseHub ->
                if (hub.name != reverseHub.name) {

                    redisService.setHubRouteResult(
                        hub.name, reverseHub.name, findOptimalRoute(hub, reverseHub, createRouteInfoGraph)
                    )
                }
            }
        }
    }

    private fun findOptimalRoute(
        startHub: Hub,
        endHub: Hub,
        createRouteInfoGraph: Map<String, List<RouteInfo>>
    ): RouteResult {

        val pq = PriorityQueue<RouteState>(compareBy { it.cumulativeTime })
        val visited = mutableSetOf<String>()

        val distances = mutableMapOf<String, Int>().apply {
            keys.forEach { this[it] = Int.MAX_VALUE }
            this[startHub.name] = 0
        }

        pq.offer(
            RouteState(
                nodeName = startHub.name,
                cumulativeDistance = 0,
                cumulativeTime = 0,
                path = mutableListOf(startHub.name)
            )
        )

        while (pq.isNotEmpty()) {

            val (currentNode, cumulativeDistance, cumulativeTime, path) = pq.poll()

            if (currentNode == endHub.name) {
                return RouteResult(
                    distance = cumulativeDistance,
                    time = cumulativeTime,
                    path = path
                )
            }

            if (currentNode in visited)
                continue

            visited.add(currentNode)

            createRouteInfoGraph[currentNode]?.forEach { route ->

                if (route.destination !in visited) {

                    val newTime = cumulativeTime?.plus(route.time!!)

                    if (newTime!! < distances.getOrDefault(route.destination!!, Int.MAX_VALUE)) {
                        distances[route.destination] = newTime

                        pq.offer(
                            RouteState(
                                nodeName = route.destination,
                                cumulativeDistance = cumulativeDistance?.plus(route.distance!!),
                                cumulativeTime = newTime,
                                path = path + route.destination
                            )
                        )

                    }

                }

            }

        }

        throw UnableCalculateRouteException()
    }

    private fun findLatitudeAndLongitude(hubPosition: String) =
        webClient
            .get()
            .uri("https://maps.googleapis.com/maps/api/geocode/json?address=$hubPosition&key=$geocoderApiKey")
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map { response ->

                val firstResult = response["results"][0]
                val geometry = firstResult["geometry"]
                val location = geometry["location"]

                mapOf(
                    "latitude" to location["lat"]?.asDouble(),
                    "longitude" to location["lng"]?.asDouble()
                )
            }
            .onErrorResume { ex ->
                Mono.error(ex)
            }
            .block()

    private fun calculateForHubRoute(startHub: Hub, endHub: Hub): HubRoute? {
        return webClient
            .get()
            .uri("https://apis-navi.kakaomobility.com/v1/directions?origin=${startHub.longitude},${startHub.latitude}&destination=${endHub.longitude},${endHub.latitude}")
            .header("Authorization", "KakaoAK $kakaoApiKey")
            .retrieve()
            .bodyToMono(JsonNode::class.java)
            .map { response ->

                val routes = response["routes"]
                val firstRoute = routes[0]
                val summary = firstRoute["summary"]

                HubRoute(
                    estimatedSecond = summary["duration"]?.asDouble(),
                    estimatedMeter = summary["distance"]?.asDouble(),
                    startHub = startHub,
                    endHub = endHub
                )
            }
            .block()
    }
}
