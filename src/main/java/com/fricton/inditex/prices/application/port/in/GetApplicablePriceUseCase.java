package com.fricton.inditex.prices.application.port.in;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.fricton.inditex.prices.domain.model.Price;

public interface GetApplicablePriceUseCase {

	/**
	 * Searches the price to be applied for the indicated product for the indicated
	 * date. If there are 2 definitions (price lists) for the product and same range
	 * of dates, the one with more priority will be returned.
	 * 
	 * @param applicationDate The date to be searched
	 * @param productId       The unique identifier of the product
	 * @param brandId         The ID of the brand
	 * @param currency        The currency of the searched price
	 * @return The found price, or empty.
	 */
	Optional<Price> execute(OffsetDateTime applicationDate, Integer productIdentifier, Integer brandId,
			String currency);

}
