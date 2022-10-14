package com.cognizant.messengerback;

import com.cognizant.messengerback.services.FileStorateService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.annotation.Resource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MessengerBackApplication implements CommandLineRunner {
	@Resource
	FileStorateService fileStorateService;

	public static void main(String[] args) {
		SpringApplication.run(MessengerBackApplication.class, args);
	}

	@Override
	public void run(String ...args) throws Exception{
		fileStorateService.deleteAll();
		fileStorateService.init();
	}
}
