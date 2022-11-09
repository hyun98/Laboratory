package com.example.reactive.service;

import com.example.reactive.domain.Stock;
import com.example.reactive.domain.StockTrade;
import com.example.reactive.domain.User;
import com.example.reactive.dto.BuyStockReq;
import com.example.reactive.dto.SellStockReq;
import com.example.reactive.repository.StockRepository;
import com.example.reactive.repository.StockTradeRepository;
import com.example.reactive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockTradeService {

    private static final Logger LOG = LoggerFactory.getLogger(StockTradeService.class);

    private final StockTradeRepository stockTradeRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    public Flux<StockTrade> getStockTradeByCompanyName(String companyName) {
        if(companyName.isBlank()) throw new RuntimeException("Need CompanyName");

        LOG.info("get Stock Trade By Company Name Service, {}", companyName);
        return stockTradeRepository.findByCompanyName(companyName)
                .log()
                .map(s -> s);
    }

    public Flux<StockTrade> getStockTradeByUserId(Long userId) {
        if(userId < 1) throw new RuntimeException("Invalid userId");

        LOG.info("get Stock Trade By userId Service, {}", userId);
        return stockTradeRepository.findByUserId(userId)
                .log()
                .map(s -> s);
    }

    public Mono<StockTrade> sellStock(SellStockReq req) {
        if(req.getUserId() < 1) throw new RuntimeException("Invalid userId");

        User user = userRepository.findById(req.getUserId()).block();
        userRepository.setUserProperty(req.getUserId(), )
        
        return stockRepository.findById(req.getStockId())
                .switchIfEmpty(Mono.error(new RuntimeException("Stock does not exists")))
                .log()
                .map(s -> s);
    }
    
    public Mono<StockTrade> buyStock(BuyStockReq req) {
        if(req.getUserId() < 1) throw new RuntimeException("Invalid userId");

    }
}
