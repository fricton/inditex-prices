package com.fricton.inditex.prices.infrastructure.db.h2.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;

public class PriceEntityUT {

	@Test
	void testPriceEntityCreation() {
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setBrandId(1);
		priceEntity.setProductId(35455);
		priceEntity.setPrice(BigDecimal.valueOf(35.50));
		priceEntity.setCurr("EUR");
		priceEntity.setStartDate(Instant.parse("2020-06-14T00:00:00Z"));
		priceEntity.setEndDate(Instant.parse("2020-06-15T00:00:00Z"));
		priceEntity.setPriceListId(1);
		priceEntity.setPriority(1);

		assertEquals(1, priceEntity.getBrandId());
		assertEquals(35455, priceEntity.getProductId());
		assertEquals(BigDecimal.valueOf(35.50), priceEntity.getPrice());
		assertEquals("EUR", priceEntity.getCurr());
		assertEquals(Instant.parse("2020-06-14T00:00:00Z"), priceEntity.getStartDate());
		assertEquals(Instant.parse("2020-06-15T00:00:00Z"), priceEntity.getEndDate());
		assertEquals(1, priceEntity.getPriority());
		assertEquals(1, priceEntity.getPriceListId());
		assertNotNull(priceEntity.toString());
	}
}
