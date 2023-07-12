package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FahrenheitCelciusConverterTest {

    @Test
    void shouldConvertCelciusToFahrenheit() {
        assertThat(FahrToCelcConverter.toFahrenheit(0)).isEqualTo(32);
        assertThat(FahrToCelcConverter.toFahrenheit(37)).isEqualTo(98);
        assertThat(FahrToCelcConverter.toFahrenheit(100)).isEqualTo(212);
    }

    @Test
    void shouldConvertFahrenheitToCelcius() {
        assertThat(FahrToCelcConverter.toCelcius(32)).isEqualTo(0);
        assertThat(FahrToCelcConverter.toCelcius(100)).isEqualTo(37);
        assertThat(FahrToCelcConverter.toCelcius(212)).isEqualTo(100);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 32",
            "37, 98",
            "100, 212"
    })
    void shouldConvertCelciusToFahrenheitParametrized(int celciusInput, int fahrenheitExpected) {
        assertThat(FahrToCelcConverter.toFahrenheit(celciusInput)).isEqualTo(fahrenheitExpected);
    }

    @ParameterizedTest
    @CsvSource({
            "32, 0",
            "98, 37",
            "212, 100"
    })
    void shouldConvertFahrenheitToCelciusParametrized(int fahrenheitInput, int celciusExpected) {
        assertThat(FahrToCelcConverter.toCelcius(fahrenheitInput)).isEqualTo(celciusExpected);
    }

}
