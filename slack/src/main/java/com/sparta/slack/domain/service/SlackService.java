package com.sparta.slack.domain.service;

import com.sparta.slack.application.dto.*;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

public interface SlackService {

    void sendMessage(SlackEvent slackEvent);

    HttpHeaders setHeadersForSlack(MediaType mediaType);

    HubDetails.Response getHubOptimizationRouteById(UUID startHubId, UUID endHubId);

    UserDetails.Response getUserDetailsById(UUID userId);

    String generateMessage(SlackEvent slackEvent);

    OrderDetails.Response getOrderById(UUID orderId);

    CompanyDetails.Response getCompanyById(UUID companyId);

    String createRequestAddressMessage(OrderDetails.Response orderDetails);

    String createStopOverMessage(UUID originHubId, UUID destinationHubId);

    String createProductMessage(OrderDetails.Response orderDetails);

    String createDeliverPersonMessage(UUID deliveryPersonId);

    String createRequestMessage(OrderDetails.Response orderDetails);

    String createOrderIdMessage(UUID orderId);

    String createRecipientNameMessage(String recipientName);

    String createStartHubMessage(UUID originHubId);

    public HubInfo.Response getHubInfoById(UUID HubId);
}
