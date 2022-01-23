package com.flightapp.admin.exception;

public class EntityAlreadyPresentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709235664384827190L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public EntityAlreadyPresentException(String message) {
		super();
		this.message = message;
	}
	

}
