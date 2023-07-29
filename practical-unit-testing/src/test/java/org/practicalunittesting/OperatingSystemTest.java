package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.practicalunittesting.OperatingSystemAssert.assertThat;

class OperatingSystemTest {

    private OperatingSystem os;
    @Test
    void testUsingMatcher() {
        OperatingSystem min9 = new Mindows9();
        assertThat(min9)
                .is128bit()
                .wasReleasedIn(2019)
                .hasVersion(9);
    }


}