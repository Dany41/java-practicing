package org.kata.bankaccount;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Account implements IAccount {

    @SneakyThrows
    public static void main(String[] args) {
        Account account = new Account();
        account.deposit(10);
        Thread.sleep(1000L);
        account.withdraw(20);
        Thread.sleep(1000L);
        account.deposit(20);
        Thread.sleep(1000L);
        account.deposit(70);
        Thread.sleep(1000L);
        account.withdraw(90);
        Thread.sleep(1000L);
        account.withdraw(90);
        Thread.sleep(1000L);
        account.deposit(100);
        Thread.sleep(1000L);
        account.withdraw(300);
        Thread.sleep(1000L);
        account.deposit(20);
        System.out.println(account.printStatement());
    }

    List<AccountTransaction> transactions = new ArrayList<>();

    private int balance;

    public int balance() {
        return this.balance;
    }

    @Override
    public void deposit(int amount) {
        this.balance += amount;
        transactions.add(
                new AccountTransaction(LocalDateTime.now(), amount, AccountTransaction.TransactionType.DEPOSIT, this.balance));
    }

    @Override
    public void withdraw(int amount) {
        this.balance -= amount;
        transactions.add(
                new AccountTransaction(LocalDateTime.now(), amount, AccountTransaction.TransactionType.WITHDRAW, this.balance));
    }

    @Override
    public String printStatement() {
        return "Date Amount Balance\n" + transactions.stream()
                .map(AccountTransaction::toString)
                .collect(Collectors.joining("\n"));
    }

    private static class AccountTransaction {
        private final LocalDateTime date;
        private final int amount;
        private final TransactionType transactionType;
        private final int balance;

        public AccountTransaction(LocalDateTime date, int amount, TransactionType transactionType, int balance) {
            this.date = date;
            this.amount = amount;
            this.transactionType = transactionType;
            this.balance = balance;
        }

        @Override
        public String toString() {
            return date.toString() + " " + transactionType.getPrefix() + amount + " " + balance;
        }

        enum TransactionType {
            DEPOSIT('+'), WITHDRAW('-');

            private char prefix;
            TransactionType(char prefix) {
                this.prefix = prefix;
            }

            public char getPrefix() {
                return this.prefix;
            }
        }
    }
}
