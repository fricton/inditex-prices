package com.fricton.inditex.prices.application.services;

import java.time.Instant;
import java.util.Optional;

import com.fricton.inditex.prices.application.port.out.PriceRepository;
import com.fricton.inditex.prices.domain.model.Price;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PriceService {
	private final PriceRepository priceRepository;

	/**
	 * Find only the price with highest priority
	 */
	public Optional<Price> getApplicablePrice(Integer brandId, Integer productId, Instant applicationDate,
			String currency) {
		return priceRepository.findPrices(brandId, productId, applicationDate, currency).stream()
				.max((p1, p2) -> p1.getPriority() - p2.getPriority());
	}
}