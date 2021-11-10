package com.bql_backend.bql_backend.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String createIntent(double amount) throws StripeException {
        Stripe.apiKey = "sk_test_51JtOaMGk0NNERUvw67ZtQ22tPLHf7BpiZnoWIwF46SxmkZ8JolU6E8HPg3bK28174aUzlZ77Kk9SkP24kpHuTeu100U7LQ9SY3";

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) amount)
                        .setCurrency("usd")
                        .addPaymentMethodType("card")
                        .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }

}
