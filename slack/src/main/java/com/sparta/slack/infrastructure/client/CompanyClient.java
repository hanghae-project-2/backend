package com.sparta.slack.infrastructure.client;

import com.sparta.slack.application.dto.CompanyDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "company-service", url = "${company.service.url}")
public interface CompanyClient {

    @GetMapping("/companies/find/{companyId}")
    CompanyDetails.Response findCompanyById(@PathVariable UUID companyId);

}
