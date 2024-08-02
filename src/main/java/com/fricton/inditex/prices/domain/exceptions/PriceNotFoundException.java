package com.fricton.inditex.prices.domain.exceptions;

@SuppressWarnings("serial")
public class PriceNotFoundException extends RuntimeException {

	public PriceNotFoundException(String message) {
		super(message);
	}
}
