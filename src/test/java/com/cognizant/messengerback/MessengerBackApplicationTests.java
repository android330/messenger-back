package com.cognizant.messengerback;

import com.cognizant.messengerback.services.KeyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MessengerBackApplicationTests {

	@Test
	void contextLoads() {
		KeyService keyService = new KeyService();
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
		String s = passwordEncoder.encode("test");
		System.err.println(s);
	}

}
