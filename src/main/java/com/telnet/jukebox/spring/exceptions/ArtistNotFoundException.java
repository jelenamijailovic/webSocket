package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9218595903700624511L;

	public ArtistNotFoundException(Long artistId) {
		System.out.println("Artist with id: " + artistId + " doesn't exist!");
	}
	
}
