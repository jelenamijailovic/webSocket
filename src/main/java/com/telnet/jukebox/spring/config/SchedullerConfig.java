package com.telnet.jukebox.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedullerConfig {

	@Autowired
	SimpMessagingTemplate template;
	
	@Scheduled(fixedDelay=3000)
	public void sendMessages() {
		template.convertAndSend("/topic/user", "This is send from scheduller");
	}
}
