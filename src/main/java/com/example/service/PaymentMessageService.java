package com.example.service;

import io.camunda.client.CamundaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentMessageService {

    private final CamundaClient client;

    public void sendPaymentStatus(String orderId, boolean success) {

        String messageName = success ? "payment_success" : "payment_failure";

        client
                .newPublishMessageCommand()
                .messageName(messageName)
                .correlationKey(orderId)
                .variables(Map.of("paymentSuccess", success))
                .send();

        System.out.println("📨 Message sent to workflow: " + messageName +
                " for orderId=" + orderId + ", success=" + success);
    }
}