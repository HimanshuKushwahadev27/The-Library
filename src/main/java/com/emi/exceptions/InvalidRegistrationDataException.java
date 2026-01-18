package com.emi.exceptions;

public class InvalidRegistrationDataException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidRegistrationDataException(String msg){
		super(msg);
	}
}
