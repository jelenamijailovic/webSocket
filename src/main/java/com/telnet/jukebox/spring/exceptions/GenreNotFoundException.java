package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5228857398164189261L;

	public GenreNotFoundException(Long genreId) {
		System.out.println("Genre with id: " + genreId + " doesn't exist!");
	}
	
}
