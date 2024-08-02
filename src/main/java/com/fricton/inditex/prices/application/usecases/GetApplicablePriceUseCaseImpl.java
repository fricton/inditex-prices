package com.fricton.inditex.prices.application.usecases;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.fricton.inditex.prices.application.port.in.GetApplicablePriceUseCase;
import com.fricton.inditex.prices.application.services.PriceService;
import com.fricton.inditex.prices.domain.model.Price;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetApplicablePriceUseCaseImpl implements GetApplicablePriceUseCase {

	private final PriceService pricesService;

	@Transactional(readOnly = true)
	@Override
	public Optional<Price> execute(OffsetDateTime applicationDate, Integer productId, Integer brandId,
			String currency) {
		return pricesService.getApplicablePrice(brandId, productId, applicationDate.toInstant(), currency);

	}

}
