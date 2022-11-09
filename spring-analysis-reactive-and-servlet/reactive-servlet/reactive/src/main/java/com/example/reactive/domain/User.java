package com.example.reactive.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    private Long id;

    private String name;

    private double property;

    public User(String name) {
        this.name = name;
        this.property = 0.0;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%d, name='%s', ='%.2f']",
                id, name, property);
    }
}
