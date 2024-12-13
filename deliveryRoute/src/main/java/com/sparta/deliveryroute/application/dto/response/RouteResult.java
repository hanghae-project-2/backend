package com.sparta.deliveryroute.application.dto.response;

import java.util.List;

public record RouteResult(
		Integer distance,
		Integer time,
		List<String> path
) {
}
