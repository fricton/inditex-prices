package com.fricton.inditex.prices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fricton.inditex.prices.application.port.in.GetApplicablePriceUseCase;
import com.fricton.inditex.prices.application.port.out.PriceRepository;
import com.fricton.inditex.prices.application.services.PriceService;
import com.fricton.inditex.prices.application.usecases.GetApplicablePriceUseCaseImpl;

@Configuration
public class SpringAppConfiguration {

	@Bean
	public PriceService priceService(PriceRepository priceRepository) {
		return new PriceService(priceRepository);
	}

	@Bean
	public GetApplicablePriceUseCase getApplicablePriceUseCase(PriceService priceService) {
		return new GetApplicablePriceUseCaseImpl(priceService);
	}
}
