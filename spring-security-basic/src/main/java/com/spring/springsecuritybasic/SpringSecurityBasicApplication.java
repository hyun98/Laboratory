package com.spring.springsecuritybasic;

import com.spring.springsecuritybasic.domain.UserRole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
public class SpringSecurityBasicApplication {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@PostConstruct
	public void setUp() {
		UserRole roleAdmin = new UserRole("ROLE_ADMIN");
		UserRole roleManager = new UserRole("ROLE_MANAGER");
		UserRole roleUser = new UserRole("ROLE_USER");
		em.persist(roleAdmin);
		em.persist(roleManager);
		em.persist(roleUser);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
