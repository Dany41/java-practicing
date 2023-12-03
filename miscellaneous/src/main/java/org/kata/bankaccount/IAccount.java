package org.kata.bankaccount;


// kata https://kata-log.rocks/banking-kata
public interface IAccount {
    void deposit(int amount);
    void withdraw(int amount);
    String printStatement();
}
