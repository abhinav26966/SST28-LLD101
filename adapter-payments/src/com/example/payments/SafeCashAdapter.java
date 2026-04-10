package com.example.payments;

import java.util.Objects;

/**
 * Adapts {@link SafeCashClient} (which uses a two-step create+confirm flow
 * and a different parameter order) to the {@link PaymentGateway} target
 * interface.
 */
public class SafeCashAdapter implements PaymentGateway {

    private final SafeCashClient client;

    public SafeCashAdapter(SafeCashClient client) {
        this.client = Objects.requireNonNull(client, "client");
    }

    @Override
    public String charge(String customerId, int amountCents) {
        Objects.requireNonNull(customerId, "customerId");
        SafeCashPayment payment = client.createPayment(amountCents, customerId);
        return payment.confirm();
    }
}
