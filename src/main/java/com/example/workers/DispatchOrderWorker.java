package com.example.workers;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class DispatchOrderWorker {

    @JobWorker(type = "dispatch-order")
    public Map<String, Object> dispatchOrder(ActivatedJob job) {
        log.info("Dispatching order: {}", job.getBpmnProcessId());
        String trackingId = "TRK-" + System.currentTimeMillis();
        log.info("Order dispatched with trackingId {}", trackingId);
        return Map.of("trackingId", trackingId);
    }
}