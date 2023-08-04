package org.practicalunittesting;

public class TransactionBuilder {

    private long id;
    private Transaction.State state;
    private boolean retryAllowed;
    private String message;
    private String billingId;

    public TransactionBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TransactionBuilder withState(Transaction.State state) {
        this.state = state;
        return this;
    }

    public TransactionBuilder withRetryAllowed(boolean retryAllowed) {
        this.retryAllowed = retryAllowed;
        return this;
    }

    public TransactionBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    public TransactionBuilder withBillingId(String billingId) {
        this.billingId = billingId;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();

        transaction.setId(this.id);
        transaction.setState(this.state);
        transaction.setRetryAllowed(this.retryAllowed);
        transaction.setMessage(this.message);
        transaction.setBillingId(this.billingId);

        return transaction;
    }

}
