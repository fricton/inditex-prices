package com.fricton.inditex.prices.infrastructure.db.h2.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.infrastructure.db.h2.entities.PriceEntity;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

	@Mapping(target = "price", expression = "java(new com.fricton.inditex.prices.domain.valueobjects.Money(entity.getPrice(), entity.getCurr()))")
	@Mapping(target = "dateRange", expression = "java(new com.fricton.inditex.prices.domain.valueobjects.DateRange(entity.getStartDate(), entity.getEndDate()))")
	Price toDomain(PriceEntity entity);

	List<Price> toDomain(List<PriceEntity> entity);

}
