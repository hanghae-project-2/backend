package com.sparta.deliveryroute.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

import static com.sparta.deliveryroute.domain.model.DeliveryStatus.WAITING;

@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryRoute {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;

	@Column(nullable = false)
	UUID deliveryId;

	@Column(nullable = false)
	UUID hubRouteId;

	@Column(nullable = false)
	Integer sequence;

	@Column(nullable = false)
	Integer actualDistance;

	@Column(nullable = false)
	Integer actualTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	DeliveryStatus status = WAITING;

	private DeliveryRoute(UUID deliveryId, UUID hubRouteId,
	                      Integer sequence, Integer actualDistance,
	                      Integer actualTime) {
		this.deliveryId = deliveryId;
		this.hubRouteId = hubRouteId;
		this.sequence = sequence;
		this.actualDistance = actualDistance;
		this.actualTime = actualTime;
	}

	public static DeliveryRoute createDeliveryRoute(UUID deliveryId,
	                                                UUID hubRouteId,
	                                                Integer sequence,
	                                                Integer actualDistance,
	                                                Integer actualTime) {
		return new DeliveryRoute(deliveryId, hubRouteId, sequence, actualDistance, actualTime);
	}

	public void updateStatus(DeliveryStatus newStatus) {
		this.status = newStatus;
	}

}
