package com.fricton.inditex.prices.infrastructure.db.h2.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.infrastructure.db.h2.entities.PriceEntity;

public class PriceEntityMapperUT {
	private PriceEntityMapper priceEntityMapper;

	@BeforeEach
	void setUp() {
		priceEntityMapper = Mappers.getMapper(PriceEntityMapper.class);
	}

	@Test
	void toDomain() {
		PriceEntity priceEntity = new PriceEntity();
		priceEntity.setBrandId(1);
		priceEntity.setProductId(35455);
		priceEntity.setPrice(BigDecimal.valueOf(35.50));
		priceEntity.setCurr("EUR");
		priceEntity.setStartDate(Instant.parse("2020-06-14T00:00:00Z"));
		priceEntity.setEndDate(Instant.parse("2020-12-31T23:59:59Z"));

		List<Price> prices = priceEntityMapper.toDomain(List.of(priceEntity));

		assertEquals(1, prices.size());
		Price price = prices.get(0);

		assertEquals(priceEntity.getBrandId(), price.getBrandId());
		assertEquals(priceEntity.getProductId(), price.getProductId());
		assertEquals(priceEntity.getPrice(), price.getPrice().getAmount());
		assertEquals(priceEntity.getCurr(), price.getPrice().getCurrency());
		assertEquals(priceEntity.getStartDate(), price.getDateRange().getFrom());
		assertEquals(priceEntity.getEndDate(), price.getDateRange().getTo());
	}

	@Test
	void toDomainNull() {
		assertNull(priceEntityMapper.toDomain((PriceEntity) null));
		assertNull(priceEntityMapper.toDomain((List<PriceEntity>) null));
	}

}
