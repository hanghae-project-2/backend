package com.sparta.order.infrastructure.client;

import com.sparta.order.application.dto.response.CompanyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "company-service")
public interface CompanyClient {

	@GetMapping("/companies/search-by-name")
	List<CompanyResponseDto> findCompaniesByName(@RequestParam("name") String name);

	@GetMapping("/companies/find/{companyId}")
	CompanyResponseDto findCompanyById(@PathVariable("companyId") UUID companyId);

	@PostMapping("/companies/batch")
	List<CompanyResponseDto> findCompaniesByIds(@RequestBody List<UUID> ids);
}
