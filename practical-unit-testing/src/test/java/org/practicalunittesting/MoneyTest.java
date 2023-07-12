package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MoneyTest {

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

}
