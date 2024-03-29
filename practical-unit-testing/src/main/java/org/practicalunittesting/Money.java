package org.practicalunittesting;

public class Money {

    private final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        if (currency == null || currency.length() < 2)
            throw new IllegalArgumentException("Illegal amount: [" + currency + "]");
        if (amount < 0)
            throw new IllegalArgumentException("Illegal amount: [" + amount + "]");
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Money money) {
            return getCurrency().equals(money.getCurrency())
                    && getAmount() == money.getAmount();
        }
        return false;
    }
}
