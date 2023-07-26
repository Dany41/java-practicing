package org.practicalunittesting;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SkipTest {

    @Test
    void someTestToRun() {
        System.out.println("Running...");
        assertThat(1 == 2).isFalse();
    }

    @Test
    @Disabled
    void someSkippedTest() {
        assertThat("skipped").isNotBlank();
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testToSkipOnWindows() {
        System.out.println(System.getProperty("os.name"));
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testToRunOnWindows() {
        System.out.println(System.getProperty("os.name"));
    }

}
