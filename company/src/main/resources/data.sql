create table company
(
    id         binary(16)                    not null
        primary key,
    hub_id     binary(16)                    not null,
    name       varchar(255)                  not null,
    address    varchar(255)                  not null,
    type       enum ('PRODUCER', 'RECEIVER') not null,
    is_delete  bit                           not null,
    is_public  bit                           not null,
    created_at datetime(6)                   null,
    created_by binary(16)                    null,
    updated_at datetime(6)                   null,
    updated_by binary(16)                    null,
    deleted_at datetime(6)                   null,
    deleted_by binary(16)                    null
);
