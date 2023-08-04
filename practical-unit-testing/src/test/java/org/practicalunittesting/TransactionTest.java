package org.practicalunittesting;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionTest {

    private static final long ANY_ID = 1;
    private static final String ANY_MESSAGE = "message";
    private static final String ANY_BILLING_ID = "a-1";
    private static final Transaction.State ANY_STATE = Transaction.State.OK;
    private static final boolean ANY_BOOLEAN = false;

    @Test
    void settersAreWorkingGoodUsingOriginConstructor() {
        Transaction transaction = new Transaction();
        transaction.setId(ANY_ID);
        transaction.setMessage(ANY_MESSAGE);
        transaction.setBillingId(ANY_BILLING_ID);
        transaction.setState(ANY_STATE);
        transaction.setRetryAllowed(ANY_BOOLEAN);

        assertThat(transaction.getId()).isEqualTo(ANY_ID);
        assertThat(transaction.getMessage()).isEqualTo(ANY_MESSAGE);
        assertThat(transaction.getBillingId()).isEqualTo(ANY_BILLING_ID);
        assertThat(transaction.getState()).isEqualTo(ANY_STATE);
        assertThat(transaction.isRetryAllowed()).isEqualTo(ANY_BOOLEAN);
    }

    @Test
    void setterAreWorkingGoodUsingTestBuilder() {
        Transaction transaction = new TransactionBuilder()
                .withId(ANY_ID)
                .withMessage(ANY_MESSAGE)
                .withBillingId(ANY_BILLING_ID)
                .withState(ANY_STATE)
                .withRetryAllowed(ANY_BOOLEAN)
                .build();

        assertThat(transaction.getId()).isEqualTo(ANY_ID);
        assertThat(transaction.getMessage()).isEqualTo(ANY_MESSAGE);
        assertThat(transaction.getBillingId()).isEqualTo(ANY_BILLING_ID);
        assertThat(transaction.getState()).isEqualTo(ANY_STATE);
        assertThat(transaction.isRetryAllowed()).isEqualTo(ANY_BOOLEAN);
    }

}