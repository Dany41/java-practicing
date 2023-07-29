package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MoneyTest {

    private static final int VALID_AMOUNT = 7;
    private final String VALID_CURRENCY = "USD";

    @ParameterizedTest
    @CsvSource({
            "10, USD",
            "15, EUR",
            "50, CHF"
    })
    void constructorShouldSetAmountAndCurrency(int amount, String currency) {
        Money money = new Money(amount, currency);

        assertThat(money.getAmount()).isEqualTo(amount);
        assertThat(money.getCurrency()).isEqualTo(currency);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -3451, -10000})
    void constructorShouldThrowIAEForInvalidAmount(int invalidAmount) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Money(invalidAmount, VALID_CURRENCY));
    }

    public static Stream<String> invalidCurrency() {
        return Stream.of(null, "", " ", "\t");
    }

    @ParameterizedTest
    @MethodSource(value = "invalidCurrency")
    void constructorShouldThrowIAEForInvalidCurrency(String invalidCurrency) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Money(VALID_AMOUNT, invalidCurrency);
                });
    }

}
