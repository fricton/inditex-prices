package com.fricton.inditex.prices.infrastructure.rest.mapper;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.infrastructure.rest.model.ProductPriceSearchResponse;

@Mapper(componentModel = "spring")
public interface PricesSearchResponseMapper {

	@Mapping(source = "dateRange.from", target = "startDate", qualifiedByName = "instantToOffsetDateTime")
	@Mapping(source = "dateRange.to", target = "endDate", qualifiedByName = "instantToOffsetDateTime")
	@Mapping(source = "price.amount", target = "price")
	@Mapping(source = "price.currency", target = "currency")
	public ProductPriceSearchResponse toDto(Price price);

	@Named("instantToOffsetDateTime")
	static OffsetDateTime instantToOffsetDateTime(Instant instant) {
		return instant != null ? instant.atOffset(ZoneOffset.UTC) : null;
	}

}
