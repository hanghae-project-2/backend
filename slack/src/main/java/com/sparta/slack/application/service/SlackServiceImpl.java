package com.sparta.slack.application.service;

import com.sparta.slack.application.dto.CompanyDetails;
import com.sparta.slack.application.dto.HubDetails;
import com.sparta.slack.application.dto.OrderDetails;
import com.sparta.slack.domain.service.SlackService;
import com.sparta.slack.infrastructure.client.CompanyClient;
import com.sparta.slack.infrastructure.client.HubClient;
import com.sparta.slack.infrastructure.client.OrderClient;
import com.sparta.slack.infrastructure.client.UserClient;
import com.sparta.slack.infrastructure.dto.SlackEvent;
import com.sparta.slack.application.dto.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@RequiredArgsConstructor
@Service
@Slf4j
public class SlackServiceImpl implements SlackService {

    public final RestTemplate restTemplate;
    public final HubClient hubClient;
    public final UserClient userClient;
    public final OrderClient orderClient;
    public final CompanyClient companyClient;

    @Value("${slack.webhook.url}")
    public String slackWebhookUrl;

    @Value("${slack.token}")
    public String slackToken;

    @Override
    public HttpHeaders setHeadersForSlack(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + slackToken);
        headers.setContentType(mediaType);
        return headers;
    }

    @Override
    public void processSlack(SlackEvent slackEvent) {
        getHubOptimizationRouteById(slackEvent.originHubId(),slackEvent.destinationHubId());
    }

   /* 주문 번호 : UUID / orderId
    주문자 정보 : 김말숙 / recipientName
    상품 정보 : 마른 오징어 50박스 / productName , productQuantity
    요청 사항 : 12월 12일 3시까지는 보내주세요! / request
    발송지 : 경기 북부 센터
    경유지 : 대전광역시 센터, 부산광역시 센터 / stopOver
    도착지 : 부산시 사하구 낙동대로 1번길 1 해산물월드 / requestAddress, requestCompanyName
    배송담당자 : 고길동 / deliverPersonName

    위 내용을 기반으로 도출된 최종 발송 시한은 12월 10일 오전 9시 입니다.*/

    @Override
    public String generateMessage(SlackEvent slackEvent){

        OrderDetails.Response orderDetails = getOrderById(slackEvent.orderId());

        String requestAddress = createRequestAddressMessage(orderDetails);
        String deliverPersonName =  createDeliverPersonMessage(slackEvent.deliveryPersonId());
        String productDetails = createProductMessage(orderDetails);
        String request = createRequestMessage(orderDetails);
        String orderId = createOrderIdMessage(slackEvent.orderId());
        String recipientName =  createRecipientNameMessage(slackEvent.recipientName());
        String stopOver = createStopOverMessage(slackEvent.originHubId(),slackEvent.destinationHubId());

        return orderId + recipientName + productDetails + request + request;
    }

    @Override
    public String createRecipientNameMessage(String recipientName){
        return "주문자 정보 : " + recipientName;
    }

    @Override
    public String createOrderIdMessage(UUID orderId){
        return "배달 번호 : " + orderId.toString();
    }

    @Override
    public String createRequestMessage(OrderDetails.Response orderDetails) {
        return "요청 사항 : " + orderDetails.getSpecialRequests();
    }

    @Override
    public String createDeliverPersonMessage(UUID deliveryPersonId) {
        return "배송 담당자 : " + getUserDetailsById(deliveryPersonId).getUsername();
    }

    @Override
    public String createProductMessage(OrderDetails.Response orderDetails) {
        String productQuantity = orderDetails.getQuantity().toString();
        String productName = orderDetails.getProductName();
        return "상품 정보 : " + productName + " " + productQuantity;
    }

    @Override
    public String createStopOverMessage(UUID originHubId, UUID destinationHubId) {
        HubDetails.Response hubDetails = getHubOptimizationRouteById(originHubId, destinationHubId);
        String stopOver = hubDetails.toString();
        return "경유지 : " + stopOver;
    }

    @Override
    public String createRequestAddressMessage(OrderDetails.Response orderDetails) {
        CompanyDetails.Response requestCompanyDetails = getCompanyById(orderDetails.getRequestCompanyId());
        String requestAddress = requestCompanyDetails.getAddress();
        String requestCompanyName = requestCompanyDetails.getName();
        return "도착지 : " + requestAddress + " " + requestCompanyName;
    }

    @Override
    public CompanyDetails.Response getCompanyById(UUID companyId) {
        return companyClient.findCompanyById(companyId);
    }

    @Override
    public OrderDetails.Response getOrderById(UUID orderId) {
        return orderClient.getOrderById(orderId);
    }

    @Override
    public UserDetails.Response getUserDetailsById(UUID userId){
        return userClient.getUserById(userId);
    }


    @Override
    public HubDetails.Response getHubOptimizationRouteById(UUID startHubId, UUID endHubId){
        return hubClient.findHubRoutesByName(startHubId, endHubId);
    }

    @Override
    public void sendMessage(String message) {

        String requestBody = "{\"text\": \"" + message + "\"}";

        HttpHeaders headers = setHeadersForSlack(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(slackWebhookUrl, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("slack 전송 성공");
        } else {
            log.error("slack 전송 실패");
        }
    }




}
