package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankAccountBDDTest {

    @Test
    void shouldBeEmptyAfterCreation() {
        // given
        // when
        BankAccount account = new BankAccount();

        // then
        int balance = account.getBalance();
        assertThat(balance).isEqualTo(0);
    }

    @Test
    void shouldAllowToCreditAccount() {
        // given
        BankAccount account = new BankAccount();
        // when
        account.deposit(100);
        // then
        int balance = account.getBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void shouldAllowToDebitAccount() {
        // given
        BankAccount account = new BankAccount();
        // when
        account.deposit(100);
        account.withdraw(40);
        // then
        int balance = account.getBalance();
        assertThat(balance).isEqualTo(60);
    }

}
