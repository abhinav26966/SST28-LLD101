package com.example.payments;

import java.util.Objects;

/**
 * Adapts {@link FastPayClient} to the {@link PaymentGateway} target interface.
 */
public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client;

    public FastPayAdapter(FastPayClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        return client.payNow(customerId, amountCents);
    }
}
