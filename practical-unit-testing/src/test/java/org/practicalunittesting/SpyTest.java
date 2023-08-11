package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyTest {

    @Test
    void creatingAndManipulatingSpy() {
        BankAccount bankAccount = spy(new BankAccount());

        when(bankAccount.getBalance()).thenReturn(100);

        bankAccount.deposit(300);
        bankAccount.withdraw(100);

        assertThat(bankAccount.getBalance()).isEqualTo(100);
    }

}
