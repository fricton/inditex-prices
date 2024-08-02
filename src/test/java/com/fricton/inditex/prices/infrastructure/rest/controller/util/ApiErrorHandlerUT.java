package com.fricton.inditex.prices.infrastructure.rest.controller.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fricton.inditex.prices.domain.exceptions.PriceNotFoundException;

public class ApiErrorHandlerUT {

	private ApiErrorHandler apiErrorHandler;

	@BeforeEach
	void setUp() {
		apiErrorHandler = new ApiErrorHandler();
	}

	@Test
	void handlePriceNotFoundException() {
		PriceNotFoundException ex = new PriceNotFoundException("Price not found");

		ResponseEntity<String> response = apiErrorHandler.handleNotFound(ex, null);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Price not found", response.getBody());
	}
}
