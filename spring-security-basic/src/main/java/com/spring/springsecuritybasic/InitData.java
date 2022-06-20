package com.spring.springsecuritybasic;

import com.spring.springsecuritybasic.domain.User;
import com.spring.springsecuritybasic.domain.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class InitData {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void init() {
        UserRole roleAdmin = new UserRole("ROLE_ADMIN");
        UserRole roleManager = new UserRole("ROLE_MANAGER");
        UserRole roleUser = new UserRole("ROLE_USER");
        User user = new User("admin", "admin", roleAdmin);
        em.persist(user);
        em.persist(roleAdmin);
        em.persist(roleManager);
        em.persist(roleUser);
    }
}
