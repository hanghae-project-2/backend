package com.sparta.delivery.application.event;

import java.util.UUID;

public record DeleteEvent(
        UUID id
) {
}
