create table delivery_route
(
    id              binary(16)                                not null
        primary key,
    delivery_id     binary(16)                                not null,
    hub_route_id    binary(16)                                not null,
    actual_distance int                                       not null,
    actual_time     int                                       not null,
    sequence        int                                       not null,
    status          enum ('ARRIVED', 'IN_TRANSIT', 'WAITING') not null,
    is_delete       bit                                       not null,
    is_public       bit                                       not null,
    created_at      datetime(6)                               null,
    created_by      binary(16)                                null,
    updated_at      datetime(6)                               null,
    updated_by      binary(16)                                null,
    deleted_at      datetime(6)                               null,
    deleted_by      binary(16)                                null
);