package com.imageservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.content.s3.enabled=false")
class ImageserviseApplicationTests {

	@Test
	void contextLoads() {
	}

}
