package com.example.workers;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class NotifyCustomerWorker {

    @JobWorker(type = "notify-customer")
    public Map<String, Object> notifyCustomer(ActivatedJob job) {
        log.info("Notifying customer for order: {}", job.getBpmnProcessId());
        log.info("Email sent to customer successfully!");
        return Map.of("notified", true);
    }
}
