package com.telnet.jukebox.spring.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrafficNotFoundException extends ClassNotFoundException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5228857398164189261L;

	public TrafficNotFoundException(Long trafficId) {
		System.out.println("Traffic with id: " + trafficId + " doesn't exist!");
	}
	
}
