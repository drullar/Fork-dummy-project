package com.appfire.deflake_flaky_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SuccessTest {

	@Test
	void successfulTest() {
		assertThat(true).isEqualTo(true);
	}
}
