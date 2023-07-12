package org.practicalunittesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UnitTestsWithoutCollaboratorsTest {

    private Map<Object, Object> mapUnderTest;

    @ParameterizedTest
    @CsvSource({
            "qwer, rewq",
            "12tr, rt21",
            "*&@*!, !*@&*"
    })
    void reverseMustCorrectlyReverseNonEmptyString(String inputString, String expectedString) {
        String reversed = UnitTestsWithoutCollaborators.reverse(inputString);
        assertThat(reversed).isEqualTo(expectedString);
    }

    @Test
    void reverseMustCorrectlyReverseEmptyString() {
        String reversed = UnitTestsWithoutCollaborators.reverse("");
        assertThat(reversed).isEmpty();
    }

    @Test
    void reverseMustCorrectlyReverseVeryLongString() {
        String reversed = UnitTestsWithoutCollaborators.reverse("xxx".repeat(100000));
        assertThat(reversed).isEqualTo("xxx".repeat(100000));
    }

    @BeforeEach
    void setUp() {
        mapUnderTest = new HashMap<>();
    }

    @ParameterizedTest
    @CsvSource({
            "1", "2", "new Object()", "saac", "new HashMap()", "null"
    })
    void objectPutToHashMapMustBeAccessibleWithGetMethod(Object input) {
        mapUnderTest.put(input, new Object());
        assertThat(mapUnderTest.get(input)).isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
            "1", "2", "new Object()", "saac", "new HashMap()", "null"
    })
    void objectPutTwiceInHashMapMustHaveLatestValue(Object input) {
        Object firstValue = new Object();
        mapUnderTest.put(input, firstValue);
        Object secondValue = new Object();
        mapUnderTest.put(input, secondValue);
        assertThat(mapUnderTest.get(input)).isEqualTo(secondValue);
    }

    @Test
    void clearMustRemovesAllMapsContentWhenIsNotEmpty() {
        Stream.generate(Object::new).limit(10).forEach(key -> mapUnderTest.put(key, new Object()));
        assertThat(mapUnderTest).isNotEmpty();
        mapUnderTest.clear();
        assertThat(mapUnderTest).isEmpty();
    }

    @Test
    void clearMustKeepMapEmptyWhenInvokedOnEmptyMap() {
        assertThat(mapUnderTest).isEmpty();
        mapUnderTest.clear();
        assertThat(mapUnderTest).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "1", "2", "new Object()", "saac", "new HashMap()", "null"
    })
    void hashMapCanContainNullValue(Object value) {
        mapUnderTest.put(null, value);
        assertThat(mapUnderTest.get(null)).isEqualTo(value);
    }
}