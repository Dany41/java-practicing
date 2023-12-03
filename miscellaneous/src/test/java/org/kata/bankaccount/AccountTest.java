package org.kata.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void depositShouldIncreaseBalance() {
        account.deposit(100);
        assertThat(account.balance()).isEqualTo(100);
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        account.withdraw(100);
        assertThat(account.balance()).isEqualTo(-100);
    }

    @Test
    void printStatementShouldBeEmptyForNewlyCreatedAccount() {
        String accountStatement = account.printStatement();
        assertThat(accountStatement).contains("Date Amount Balance");
    }

    @Test
    void printStatementShouldHoldInfoAboutDeposit() {
        account.deposit(100);
        String accountStatement = account.printStatement();
        assertThat(accountStatement).contains("Date Amount Balance");
        assertThat(accountStatement).contains("+100"); // amount column
        assertThat(accountStatement).contains(" 100"); // balance column
    }

    @Test
    void printStatementShouldHoldInfoAboutWithdraw() {
        account.withdraw(100);
        account.withdraw(200);
        String accountStatement = account.printStatement();
        assertThat(accountStatement).contains("Date Amount Balance");
        assertThat(accountStatement).contains("-100"); // amount column
        assertThat(accountStatement).contains("-200"); // amount column
        assertThat(accountStatement).contains("-300"); // balance column
    }

}