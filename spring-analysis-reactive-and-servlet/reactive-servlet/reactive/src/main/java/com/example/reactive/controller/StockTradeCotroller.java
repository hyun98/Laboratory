package com.example.reactive.controller;

import com.example.reactive.domain.StockTrade;
import com.example.reactive.dto.BuyStockReq;
import com.example.reactive.dto.SellStockReq;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/trade")
public interface StockTradeCotroller {

    @GetMapping(
            value = "/company/{companyName}",
            produces = "application/json")
    Flux<StockTrade> getStockTradeByCompanyName(@PathVariable String companyName);

    @GetMapping(
            value    = "/user/{userId}",
            produces = "application/json")
    Flux<StockTrade> getStockTradeByUserId(@PathVariable Long userId);

    @PostMapping(
            value = "/sell",
            consumes = "application/json",
            produces = "application/json"
    )
    Mono<StockTrade> sellStock(@RequestBody SellStockReq request);

    @PostMapping(
            value = "/buy",
            consumes = "application/json",
            produces = "application/json"
    )
    Mono<StockTrade> buyStock(@RequestBody BuyStockReq request);
}
