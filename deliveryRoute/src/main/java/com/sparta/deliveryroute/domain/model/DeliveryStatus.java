package com.sparta.deliveryroute.domain.model;

import com.sparta.deliveryroute.domain.exception.deliveryRoute.IncorrectDeliveryTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {

	WAITING("WAITING", "배송 준비 중"),
	IN_TRANSIT("IN_TRANSIT", "배송 중"),
	ARRIVED("ARRIVED", "배송 완료");

	private final String key;
	private final String description;

	public static DeliveryStatus fromKey(String key) {

		return Arrays.stream(DeliveryStatus.values())
				.filter(type -> type.key.equals(key))
				.findFirst()
				.orElseThrow(IncorrectDeliveryTypeException::new);
	}
}
