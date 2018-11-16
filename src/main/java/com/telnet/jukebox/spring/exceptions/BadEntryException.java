package com.telnet.jukebox.spring.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadEntryException extends IOException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9010214624259684608L;

	public BadEntryException() {
		System.out.println("Error during inserting!");
	}

}
