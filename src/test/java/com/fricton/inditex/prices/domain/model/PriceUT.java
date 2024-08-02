package com.fricton.inditex.prices.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;

import com.fricton.inditex.prices.domain.valueobjects.DateRange;
import com.fricton.inditex.prices.domain.valueobjects.Money;

public class PriceUT {
	
	@Test
	void testPriceCreation() {
		Integer brandId = 1;
		Integer productId = 35455;
		Money priceAmount = new Money(BigDecimal.valueOf(35.50), "EUR");
		DateRange dateRange = new DateRange(Instant.parse("2020-06-14T00:00:00Z"),
				Instant.parse("2020-12-31T23:59:59Z"));
		Integer priority = 1;
		Integer priceListId = 1;

		Price price = new Price(productId, brandId, dateRange, priceListId, priority, priceAmount);

		assertEquals(brandId, price.getBrandId());
		assertEquals(productId, price.getProductId());
		assertEquals(priceAmount, price.getPrice());
		assertEquals(dateRange, price.getDateRange());
		assertEquals(priceListId, price.getPriceListId());
		assertEquals(priority, price.getPriority());
	}

	@Test
	void testPriceSettersAndGetters() {
		Price price = new Price();

		Integer brandId = 1;
		Integer productId = 35455;
		Money priceAmount = new Money(BigDecimal.valueOf(35.50), "EUR");
		DateRange dateRange = new DateRange(Instant.parse("2020-06-14T00:00:00Z"),
				Instant.parse("2020-12-31T23:59:59Z"));
		Integer priority = 1;
		Integer priceListId = 1;

		price.setBrandId(brandId);
		price.setProductId(productId);
		price.setPrice(priceAmount);
		price.setDateRange(dateRange);
		price.setPriority(priority);
		price.setPriceListId(priceListId);

		assertEquals(brandId, price.getBrandId());
		assertEquals(productId, price.getProductId());
		assertEquals(priceAmount, price.getPrice());
		assertEquals(dateRange, price.getDateRange());
		assertEquals(priority, price.getPriority());
		assertEquals(priceListId, price.getPriceListId());
		assertNotNull(price.toString());
	}
}
