package com.example.workers;


import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ProcessPaymentWorker {

    @JobWorker(type = "process-payment")
    public Map<String, Object> processPayment(ActivatedJob job) {
        log.info("Processing payment for: {}", job.getBpmnProcessId());
        // Always return PENDING so the BPMN waits for message event
        return Map.of("paymentStatus", "PENDING");
    }
}