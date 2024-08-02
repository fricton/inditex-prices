package com.fricton.inditex.prices.infrastructure.rest.controller.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fricton.inditex.prices.domain.exceptions.PriceNotFoundException;

@ControllerAdvice
public class ApiErrorHandler {

	@ExceptionHandler({ PriceNotFoundException.class })
	public ResponseEntity<String> handleNotFound(PriceNotFoundException e, HttpServletRequest request) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
