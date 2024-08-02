package com.fricton.inditex.prices.domain.valueobjects;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Money {

	private final BigDecimal amount;
	private final String currency;

}
