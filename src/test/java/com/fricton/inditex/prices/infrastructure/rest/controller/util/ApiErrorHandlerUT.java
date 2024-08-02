package com.fricton.inditex.prices.infrastructure.rest.controller.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

		ResponseEntity<Object> response = apiErrorHandler.handlePriceNotFoundException(ex);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Price not found", ((ApiError) response.getBody()).getMessage());
	}
	
	@Test
	void handleMethodArgumentNotValidException() {
		FieldError fieldError = new FieldError("PriceRequest", "brandId", "Error in param");
        BindException bindException = new BindException(new Object(), "brandId");
        bindException.addError(fieldError);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindException);

        MockHttpServletRequest request = new MockHttpServletRequest();
        WebRequest webRequest = new ServletWebRequest(request);

        ResponseEntity<Object> response = apiErrorHandler.handleMethodArgumentNotValid(ex, null, HttpStatus.BAD_REQUEST, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError apiError = (ApiError) response.getBody();
        assertEquals("Validation Failed", apiError.getMessage());
        assertEquals(1, apiError.getErrors().size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
    void handleMethodArgumentTypeMismatch() {
		MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getName()).thenReturn("brandId");
        when(ex.getValue()).thenReturn("AAAA");

        Class<Integer> type = Integer.class;
        when(ex.getRequiredType()).thenReturn((Class) type);

        ResponseEntity<Object> response = apiErrorHandler.handleMethodArgumentTypeMismatch(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError apiError = (ApiError) response.getBody();
        assertEquals("The parameter 'brandId' of value 'AAAA' could not be converted to type 'Integer'", apiError.getMessage());

    }
}
