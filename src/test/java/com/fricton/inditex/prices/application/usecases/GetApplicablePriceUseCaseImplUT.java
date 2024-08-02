package com.fricton.inditex.prices.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fricton.inditex.prices.application.services.PriceService;
import com.fricton.inditex.prices.domain.model.Price;

public class GetApplicablePriceUseCaseImplUT {

	@Mock
	private PriceService priceService;

	@InjectMocks
	private GetApplicablePriceUseCaseImpl getApplicablePriceUseCase;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void execute_ReturnsApplicablePrice() {
		Integer brandId = 1;
		Integer productId = 35455;
		String currency = "EUR";
		Instant applicationDate = Instant.parse("2024-07-31T16:00:00.000Z");

		Price expectedPrice = new Price();
		when(priceService.getApplicablePrice(brandId, productId, applicationDate, currency))
				.thenReturn(Optional.of(expectedPrice));

		Optional<Price> result = getApplicablePriceUseCase.execute(applicationDate.atOffset(ZoneOffset.UTC), productId,
				brandId, currency);

		assertEquals(expectedPrice, result.get());
		verify(priceService, times(1)).getApplicablePrice(brandId, productId, applicationDate, currency);
	}

}
