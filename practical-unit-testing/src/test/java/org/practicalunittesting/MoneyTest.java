package org.practicalunittesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {

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

}
