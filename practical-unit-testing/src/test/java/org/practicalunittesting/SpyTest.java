package org.practicalunittesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpyTest {

    @Spy
    BankAccount bankAccount = new BankAccount();

    @Test
    void creatingAndManipulatingSpy() {
//        BankAccount bankAcc = new BankAccount();
//        BankAccount bankAccount = spy(bankAcc);

        when(bankAccount.getBalance()).thenReturn(100);

        bankAccount.deposit(300);
        bankAccount.withdraw(100);

        assertThat(bankAccount.getBalance()).isEqualTo(100);
    }

}
