package com.telnet.jukebox.spring.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.telnet.jukebox.spring.model.User;

@Controller
public class WebSocketController {

	@MessageMapping("/user")
	@SendTo("/topic/user")
	public String getUser(User user) throws InterruptedException {
		Thread.sleep(2000);
		return "User is: "+user.getName();
	}
	
}
