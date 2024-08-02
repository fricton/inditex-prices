package com.fricton.inditex.prices.infrastructure.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.ResponseEntity;

import com.fricton.inditex.prices.application.usecases.GetApplicablePriceUseCaseImpl;
import com.fricton.inditex.prices.domain.exceptions.PriceNotFoundException;
import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.infrastructure.rest.mapper.PricesSearchResponseMapper;
import com.fricton.inditex.prices.infrastructure.rest.mapper.PricesSearchResponseMapperImpl;
import com.fricton.inditex.prices.infrastructure.rest.model.ProductPriceSearchResponse;

public class PricesControllerUT {

	@Mock
	private GetApplicablePriceUseCaseImpl getApplicablePriceUseCase;

	@Spy
	private PricesSearchResponseMapper pricesSearchResponseMapper = new PricesSearchResponseMapperImpl();

	@InjectMocks
	private PricesController pricesController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getApplicablePrice_ReturnsPriceOk() {
		Integer brandId = 1;
		Integer productId = 35455;
		String applicationDate = "2024-07-31T16:00:00.000Z";

		OffsetDateTime applicationDateParsed = OffsetDateTime.parse(applicationDate);
		Price price = new Price();
		ProductPriceSearchResponse priceResponse = new ProductPriceSearchResponse();

		when(getApplicablePriceUseCase.execute(applicationDateParsed, productId, brandId, null))
				.thenReturn(Optional.of(price));
		when(pricesSearchResponseMapper.toDto(price)).thenCallRealMethod();

		ResponseEntity<ProductPriceSearchResponse> response = pricesController.getProductPrice(applicationDateParsed,
				productId, brandId, null);

		assertEquals(ResponseEntity.ok(priceResponse), response);
		verify(getApplicablePriceUseCase, times(1)).execute(applicationDateParsed, productId, brandId, null);
		verify(pricesSearchResponseMapper, times(1)).toDto(price);
	}

	@Test
	void getApplicablePrice_ReturnsPriceNotOk() {
		Integer brandId = 1;
		Integer productId = 35455;
		String applicationDate = "2024-07-31T16:00:00.000Z";

		OffsetDateTime applicationDateParsed = OffsetDateTime.parse(applicationDate);

		when(getApplicablePriceUseCase.execute(applicationDateParsed, productId, brandId, null))
				.thenReturn(Optional.empty());

		PriceNotFoundException expectedException = null;
		try {
			pricesController.getProductPrice(applicationDateParsed, productId, brandId, null);
		} catch (PriceNotFoundException ex) {
			expectedException = ex;
		}

		assertNotNull(expectedException);
		verify(getApplicablePriceUseCase, times(1)).execute(applicationDateParsed, productId, brandId, null);
	}

}
