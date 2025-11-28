package com.example.workers;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import io.camunda.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@Slf4j
public class ValidateOrderWorker {

    @JobWorker(type = "validate-order")
    public Map<String, Object> validateOrder(ActivatedJob job) {
        Map<String, Object> variables = job.getVariablesAsMap();
        log.info("Validating order: {}", variables);
        String orderId = variables.get("orderId").toString();
        double amount = Double.parseDouble(variables.get("amount").toString());
        if (amount <= 0) {
            throw new RuntimeException("INVALID_AMOUNT");
        }
        log.info("Order {} validated successfully", orderId);
        return Map.of("isValid", true);
    }
}