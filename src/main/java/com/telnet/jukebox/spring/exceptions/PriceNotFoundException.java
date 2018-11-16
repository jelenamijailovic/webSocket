package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5228857398164189261L;

	public PriceNotFoundException(Long priceId) {
		System.out.println("Price with id: " + priceId + " doesn't exist!");
	}
	
}
