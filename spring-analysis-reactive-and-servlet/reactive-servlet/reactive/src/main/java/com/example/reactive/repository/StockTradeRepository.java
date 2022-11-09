package com.example.reactive.repository;

import com.example.reactive.domain.StockTrade;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StockTradeRepository extends ReactiveCrudRepository<StockTrade, Long> {

    Flux<StockTrade> findByUserId(Long userId);

    
//    @Query("SELECT * FROM stocktrade WHERE companyName = :companyName")
//    Flux<StockTrade> findByCompanyName(String companyName);
    
    Flux<StockTrade> findByCompanyName(String companyName);
}
