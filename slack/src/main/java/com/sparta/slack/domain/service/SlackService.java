package com.sparta.slack.domain.service;

import com.sparta.slack.application.dto.CompanyDetails;
import com.sparta.slack.application.dto.HubDetails;
import com.sparta.slack.application.dto.OrderDetails;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import com.sparta.slack.application.dto.UserDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.UUID;

public interface SlackService {

    void sendMessage(String text);

    HttpHeaders setHeadersForSlack(MediaType mediaType);

    void processSlack(SlackEvent slackEvent);

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
}
