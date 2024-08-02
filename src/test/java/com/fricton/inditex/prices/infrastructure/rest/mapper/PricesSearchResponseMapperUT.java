package com.fricton.inditex.prices.infrastructure.rest.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.domain.valueobjects.DateRange;
import com.fricton.inditex.prices.domain.valueobjects.Money;
import com.fricton.inditex.prices.infrastructure.rest.model.ProductPriceSearchResponse;

public class PricesSearchResponseMapperUT {

	private PricesSearchResponseMapper pricesSearchResponseMapper;

	@BeforeEach
	void setUp() {
		pricesSearchResponseMapper = Mappers.getMapper(PricesSearchResponseMapper.class);
	}

	@Test
	void toDto() {
		// Create a Price instance
		Price price = new Price();
		price.setBrandId(1);
		price.setProductId(35455);
		price.setPrice(new Money(BigDecimal.valueOf(35.50), "EUR"));
		price.setDateRange(new DateRange(Instant.parse("2020-06-14T00:00:00Z"), Instant.parse("2020-12-31T23:59:59Z")));
		price.setPriority(1);
		price.setPriceListId(1);

		// Map to ProductPriceSearchResponse DTO
		ProductPriceSearchResponse response = pricesSearchResponseMapper.toDto(price);

		// Verify the mapped values
		assertNotNull(response);
		assertEquals(price.getBrandId(), response.getBrandId());
		assertEquals(price.getProductId(), response.getProductId());
		assertEquals(BigDecimal.valueOf(35.50), response.getPrice());
		assertEquals("EUR", response.getCurrency());
		assertEquals(OffsetDateTime.parse("2020-06-14T00:00:00Z"), response.getStartDate());
		assertEquals(OffsetDateTime.parse("2020-12-31T23:59:59Z"), response.getEndDate());
		assertEquals(price.getPriceListId(), response.getPriceListId());
	}
	
	@Test
	void toDtoNull() {
		assertNull(pricesSearchResponseMapper.toDto(null));
	}
}
