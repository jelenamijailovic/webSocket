package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SongNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8469837057309578354L;

	public SongNotFoundException(Long songId) {
		System.out.println("Song with id: " + songId + " doesn't exist!");
		//return new ResponseEntity<> ("Song with id: " + songId + " doesn't exist!", HttpStatus.NOT_FOUND); 
	}
	
}
