package com.lti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserRestAppApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertFalse(10>20,"10 is less than 20");
	}

}
