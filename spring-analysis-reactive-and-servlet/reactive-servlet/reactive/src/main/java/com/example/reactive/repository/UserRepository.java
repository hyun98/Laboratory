package com.example.reactive.repository;

import com.example.reactive.domain.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Modifying
    @Query("UPDATE user SET property = :change + property where id = :userId")
    Mono<User> setUserProperty(Long userId, double change);
}
