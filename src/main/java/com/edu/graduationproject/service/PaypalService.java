package com.edu.graduationproject.service;

import com.edu.graduationproject.model.EPaypalPaymentIntent;
import com.edu.graduationproject.model.EPaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {
    public Payment createPayment(
            Double total,
            String currency,
            EPaypalPaymentMethod method,
            EPaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}