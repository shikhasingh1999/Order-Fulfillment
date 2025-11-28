package com.example.controller;

import com.example.dto.OrderRequest;
import io.camunda.client.CamundaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.example.utils.Constants.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final CamundaClient client;

    @PostMapping("/orders")
    public Map<String, Object> startOrder (@RequestBody OrderRequest orderRequest) {
        log.info("Received new order request: {}", orderRequest);

        var variables = Map.of(
                ORDER_ID, orderRequest.getOrderId(),
                AMOUNT, orderRequest.getAmount(),
                ITEMS, orderRequest.getItems()
        );

        var result = client
                .newCreateInstanceCommand()
                .bpmnProcessId("order-fulfillment-process-bpmn")
                .latestVersion()
                .variables(variables)
                .send()
                .join();

        long processInstanceKey = result.getProcessInstanceKey();

        log.info("Started process instance: {}", processInstanceKey);

        return Map.of(
                "status", "STARTED",
                "processInstanceKey", processInstanceKey
        );
    }
}