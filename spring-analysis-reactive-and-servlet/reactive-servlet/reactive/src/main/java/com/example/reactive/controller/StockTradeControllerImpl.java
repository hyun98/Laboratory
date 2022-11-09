package com.example.reactive.controller;

import com.example.reactive.domain.StockTrade;
import com.example.reactive.dto.BuyStockReq;
import com.example.reactive.dto.SellStockReq;
import com.example.reactive.service.StockTradeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StockTradeControllerImpl implements StockTradeCotroller{
    
    private static final Logger LOG = LoggerFactory.getLogger(StockTradeControllerImpl.class);

    private final StockTradeService stockTradeService;

    @Override
    public Flux<StockTrade> getStockTradeByCompanyName(String companyName) {
        return stockTradeService.getStockTradeByCompanyName(companyName);
    }

    @Override
    public Flux<StockTrade> getStockTradeByUserId(Long userId) {
        return stockTradeService.getStockTradeByUserId(userId);
    }

    @Override
    public Mono<StockTrade> sellStock(SellStockReq request) {
        return null;
    }

    @Override
    public Mono<StockTrade> buyStock(BuyStockReq request) {
        return null;
    }
}
