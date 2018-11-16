package com.telnet.jukebox.spring.exceptions;

import javax.security.auth.login.LoginException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadLoginException extends LoginException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7509724746840415425L;

	public BadLoginException() {
		System.out.println("Wrong username or password!!!");
	}
}
