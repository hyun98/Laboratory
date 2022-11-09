package com.example.reactive.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "stock")
public class Stock {
    @Id
    private Long id;

    @Column("company_name")
    private String companyName;

    private double price;

    public Stock(String companyName, double price) {
        this.companyName = companyName;
        this.price = price;
    }
    
}
