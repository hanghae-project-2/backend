package com.sparta.deliveryroute.infrastructure.client.response;

import java.util.List;

public record RouteResult(
		Integer distance,
		Integer time,
		List<String> path
) {
}
