package com.spring.springsecuritybasic.repository;

import com.spring.springsecuritybasic.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByRoleName(String roleName);
}
