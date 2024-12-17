package com.sparta.order.application.event;

import java.util.UUID;

public record DeleteEvent(
        UUID id
) {
}
