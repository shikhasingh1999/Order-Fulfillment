package com.example.dto;

import lombok.Data;

@Data
public class PaymentCallbackRequest {

    private String orderId;
    private String status;
}
