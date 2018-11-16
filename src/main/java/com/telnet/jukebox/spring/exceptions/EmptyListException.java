package com.telnet.jukebox.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmptyListException extends ClassNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8096956597983362993L;

	public EmptyListException() {
		System.out.println("Error occured while requesting list!");
	}
	
}
