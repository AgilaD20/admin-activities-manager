package com.flightapp.admin.exception;

public class FlightNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6368014132348952748L;
	private String message;
	public FlightNotFoundException(String message) {
		this.message = message;
	}

}