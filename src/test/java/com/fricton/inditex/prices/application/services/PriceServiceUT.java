package com.fricton.inditex.prices.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fricton.inditex.prices.application.port.out.PriceRepository;
import com.fricton.inditex.prices.domain.model.Price;

public class PriceServiceUT {

	@Mock
	private PriceRepository priceRepository;

	@InjectMocks
	private PriceService priceService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getApplicablePrice_ReturnsPrice() {
		Integer brandId = 1;
		Integer productId = 35455;
		String currency = "EUR";
		Instant applicationDate = Instant.parse("2024-07-31T16:00:00.000Z");

		Price expectedPrice = new Price();
		expectedPrice.setPriority(2);

		Price lowPriorityPrice0 = new Price();
		lowPriorityPrice0.setPriority(0);

		Price lowPriorityPrice1 = new Price();
		lowPriorityPrice1.setPriority(1);

		when(priceRepository.findPrices(brandId, productId, applicationDate, currency))
				.thenReturn(List.of(lowPriorityPrice0, expectedPrice, lowPriorityPrice1));

		Optional<Price> result = priceService.getApplicablePrice(brandId, productId, applicationDate, currency);

		assertEquals(expectedPrice, result.get());
		verify(priceRepository, times(1)).findPrices(brandId, productId, applicationDate, currency);
	}
}
