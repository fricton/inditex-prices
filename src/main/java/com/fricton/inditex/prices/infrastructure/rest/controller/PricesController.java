package com.fricton.inditex.prices.infrastructure.rest.controller;

import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.openapitools.api.V1Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.fricton.inditex.prices.application.port.in.GetApplicablePriceUseCase;
import com.fricton.inditex.prices.domain.exceptions.PriceNotFoundException;
import com.fricton.inditex.prices.infrastructure.rest.mapper.PricesSearchResponseMapper;
import com.fricton.inditex.prices.infrastructure.rest.model.ProductPriceSearchResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@RestController
@Slf4j
@Validated
public class PricesController implements V1Api {

	private final PricesSearchResponseMapper mapper;

	private final GetApplicablePriceUseCase searchPriceByParams;
	
	@Value(value = "${rest.search.default_currency}")
	private String DEFAULT_CURRENCY;

	@Override
	public ResponseEntity<ProductPriceSearchResponse> getProductPrice(@NotNull @Valid OffsetDateTime applicationDate,
			@NotNull @Valid Integer productId, @Valid Integer brandId, @Size(min = 3, max = 3) @Valid String currency) {

		log.info("Received prices search");
		log.debug(String.format(
				"PARAMS productId: %s, brandId: %s, applicationDate: %s, currency: %s",
				productId, brandId, applicationDate, currency));

		return searchPriceByParams.execute(applicationDate, productId, brandId, currency != null ? currency : DEFAULT_CURRENCY)
				.map(price -> new ResponseEntity<ProductPriceSearchResponse>(mapper.toDto(price), HttpStatus.OK))
				.orElseThrow(() -> new PriceNotFoundException(String.format(
						"Prices not found for params productId: %s, brandId: %s, applicationDate: %s, currency: %s",
						productId, brandId, applicationDate, currency)));

	}

}
