package com.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Long orderId;
    private List<String> items;
    private Double amount;
}
