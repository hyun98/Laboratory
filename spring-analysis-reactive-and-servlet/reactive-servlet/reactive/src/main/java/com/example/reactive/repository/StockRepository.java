package com.example.reactive.repository;

import com.example.reactive.domain.Stock;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StockRepository extends ReactiveCrudRepository<Stock, Long> {
    
}
