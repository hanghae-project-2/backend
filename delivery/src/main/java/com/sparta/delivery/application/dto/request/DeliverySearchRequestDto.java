package com.sparta.delivery.application.dto.request;

public record DeliverySearchRequestDto(
        String name,
        String type,
        String address)
{
    public DeliverySearchRequestDto{};

    public DeliverySearchRequestDto(){
        this(null,null,null);
    };
}
