package com.example.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuyStockReq {
    private Long userId;
    private Long stockId;
    private int quantity;
}
