package org.practicalunittesting;

public class BankAccount {
    private int balance;
    public int getBalance() {
        return this.balance;
    }

    public void deposit(int i) {
        this.balance += i;
    }

    public void withdraw(int i) {
        this.balance -= i;
    }
}
