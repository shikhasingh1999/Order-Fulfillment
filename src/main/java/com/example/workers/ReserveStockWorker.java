package com.example.workers;

import io.camunda.client.annotation.JobWorker;
import io.camunda.client.annotation.Variable;
import io.camunda.client.api.response.ActivatedJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class ReserveStockWorker {

    @JobWorker(type = "reserve-stock")
    public Map<String, Object> reserveStock(ActivatedJob job) {
        log.info("Reserving stock for: {}", job.getKey());
        // Fake reservation ID
        String reservationId = UUID.randomUUID().toString();
        log.info("Stock reserved with reservationId: {}", reservationId);
        return Map.of("reservationId", reservationId);
    }
}
