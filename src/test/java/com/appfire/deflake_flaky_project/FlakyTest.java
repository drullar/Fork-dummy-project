package com.appfire.deflake_flaky_project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FlakyTest {

    @Test
    void flakyTest() {
        Random random = new Random();
        assertThat(random.nextBoolean()).isEqualTo(random.nextBoolean());
    }
}
