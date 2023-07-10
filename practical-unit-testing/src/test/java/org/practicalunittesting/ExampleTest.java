package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {


    @Test
    void testMethod() {

        assertThat("some string").isNotEmpty();
        assertThat(1).isLessThan(2);
        assertThat(1).isGreaterThanOrEqualTo(0);
        assertThat(true).isTrue();
        assertThat(Arrays.asList(1, 2, 3)).contains(1);

        assertThat("some string")
                .isNotEmpty()
                .hasSize(10)
                .matches("some.*");

    }

}
