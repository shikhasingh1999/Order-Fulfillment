package com.example.controller;

import com.example.dto.PaymentCallbackRequest;
import io.camunda.client.CamundaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentCallbackController {

    private final CamundaClient client;

    @PostMapping("/callback")
    public Map<String, Object> processPaymentCallback (@RequestBody PaymentCallbackRequest paymentCallbackRequest) {
        log.info("Payment callback received: {}", paymentCallbackRequest);

        client
                .newPublishMessageCommand()
                .messageName("payment-confirmation")
                .correlationKey(paymentCallbackRequest.getOrderId())
                .variables(Map.of("paymentStatus", paymentCallbackRequest.getStatus()))
                .send()
                .join();

        return Map.of(
                "message", "Payment message published",
                "orderId", paymentCallbackRequest.getOrderId()
        );

    }


}
