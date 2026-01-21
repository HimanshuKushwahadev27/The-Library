package com.emi.exceptions;

public class ContentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ContentNotFoundException(String msdg){
		super(msdg);
	}

}
