package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1530515260551930889L;

	public UserNotFoundException(Long userId) {
		System.out.println("User with id: " + userId + " doesn't exist!");
	}
	
}
