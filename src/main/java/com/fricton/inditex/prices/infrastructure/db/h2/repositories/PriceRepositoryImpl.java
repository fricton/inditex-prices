package com.fricton.inditex.prices.infrastructure.db.h2.repositories;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.fricton.inditex.prices.application.port.out.PriceRepository;
import com.fricton.inditex.prices.domain.model.Price;
import com.fricton.inditex.prices.infrastructure.db.h2.mappers.PriceEntityMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

	private final JpaPriceRepository jpaPriceRepository;
	private final PriceEntityMapper priceEntityMapper;

	@Override
	public List<Price> findPrices(Integer brandId, Integer productId, Instant applicationDate, String currency) {
		return jpaPriceRepository.findPrices(brandId, productId, currency, applicationDate).stream()
				.map(priceEntityMapper::toDomain).collect(Collectors.toList());
	}

}
