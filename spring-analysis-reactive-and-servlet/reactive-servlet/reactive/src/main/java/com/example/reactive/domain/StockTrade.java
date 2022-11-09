package com.example.reactive.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "stocktrade")
public class StockTrade {

    @Id
    private Long id;

    @Column("time_stamp")
    private LocalDateTime timeStamp;

    @Column("company_name")
    private String companyName;

    private double price;

    @Column("trade_quantity")
    private int tradeQuantity;

    @Column("trade_action")
    private TradeAction tradeAction;

    @Column("user_id")
    private Long userId;

    public StockTrade(String companyName, double price, int tradeQuantity, TradeAction tradeAction, Long userId) {
        this.timeStamp = LocalDateTime.now();
        this.companyName = companyName;
        this.price = price;
        this.tradeQuantity = tradeQuantity;
        this.tradeAction = tradeAction;
        this.userId = userId;
    }
}

