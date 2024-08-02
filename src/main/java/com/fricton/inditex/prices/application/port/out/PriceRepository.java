package com.fricton.inditex.prices.application.port.out;

import java.time.Instant;
import java.util.List;

import com.fricton.inditex.prices.domain.model.Price;

public interface PriceRepository {

	List<Price> findPrices(Integer brandId, Integer productId, Instant applicationDate, String currency);
}
