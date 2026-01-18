package com.emi.exceptions;

public class RoleNotAllowedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RoleNotAllowedException(String msg)
	{
		super(msg);
	}
}
