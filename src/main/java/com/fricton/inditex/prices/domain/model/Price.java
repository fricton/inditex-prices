package com.fricton.inditex.prices.domain.model;

import com.fricton.inditex.prices.domain.valueobjects.DateRange;
import com.fricton.inditex.prices.domain.valueobjects.Money;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

	private Integer productId;

	private Integer brandId;

	private DateRange dateRange;

	private Integer priceListId;

	private Integer priority;

	private Money price;

}
