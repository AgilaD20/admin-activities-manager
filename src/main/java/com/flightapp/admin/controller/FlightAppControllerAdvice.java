package com.flightapp.admin.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flightapp.admin.exception.EntityAlreadyPresentException;
import com.flightapp.admin.exception.FlightNotFoundException;
import com.flightapp.admin.ui.ErrorResponse;

@RestControllerAdvice
public class FlightAppControllerAdvice {
	
	
	
	@ExceptionHandler
	public ErrorResponse handleFlightNotFoundException(FlightNotFoundException ex)
	{
		return new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
	}
	
	@ExceptionHandler
	public ErrorResponse handleEntityAlreadyPresentException(EntityAlreadyPresentException ex)
	{
		return new ErrorResponse(ex.getMessage(),HttpStatus.NOT_MODIFIED.value(),LocalDateTime.now());
	}
	
	

}
