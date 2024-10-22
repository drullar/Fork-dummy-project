package com.appfire.deflake_flaky_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DeflakeFlakyProjectApplicationTests {

	@Test
	void successfulTest() {
		System.out.println("Running: Successful Test");
		assertThat(true).isEqualTo(true);
	}

	@Test
	void flakyTest() {
		System.out.println("Running: Flaky Test");
		Random random = new Random();
		assertThat(random.nextBoolean()).isEqualTo(random.nextBoolean());
	}
}
