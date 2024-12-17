package com.sparta.deliveryroute.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public record RouteResult(
		@JsonProperty("distance")
		Integer distance,
		@JsonProperty("time")
		Integer time,
		@JsonProperty("path")
		@JsonDeserialize(as = ArrayList.class, contentAs = String.class)
		List<String> path
) {
}
