package com.example.springboot.datademo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DataDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void whenAssertingEquality_thenEqual() {
		String expected = "Harry";
		String actual = "Harry";

		assertEquals(expected, actual);
	}

}
