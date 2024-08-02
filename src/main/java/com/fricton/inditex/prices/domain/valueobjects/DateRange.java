package com.fricton.inditex.prices.domain.valueobjects;

import java.time.Instant;

import lombok.Value;

@Value
public class DateRange {

	private final Instant from;
	private final Instant to;

}
