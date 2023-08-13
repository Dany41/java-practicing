package org.practicalunittesting;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class MoneyAssertions extends AbstractAssert<MoneyAssertions, Money> {
    protected MoneyAssertions(Money money, Class<?> selfType) {
        super(money, selfType);
    }

    public static MoneyAssertions assertThat(Money actual) {
        return new MoneyAssertions(actual, MoneyAssertions.class);
    }

    public MoneyAssertions hasAmount(int amount) {
        isNotNull();
        Assertions.assertThat(actual.getAmount()).isEqualTo(amount);
        return this;
    }

    public MoneyAssertions hasCurrency(String currency) {
        isNotNull();
        Assertions.assertThat(actual.getCurrency()).isEqualTo(currency);
        return this;
    }
}
