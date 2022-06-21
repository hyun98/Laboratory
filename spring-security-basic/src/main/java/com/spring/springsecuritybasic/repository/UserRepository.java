package com.spring.springsecuritybasic.repository;

import com.spring.springsecuritybasic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
    
    
}
