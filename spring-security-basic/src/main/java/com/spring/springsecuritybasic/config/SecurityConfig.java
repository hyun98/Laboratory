package com.spring.springsecuritybasic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity // spring security filter가 springFilterChain에 등록이 됨
public class SecurityConfig {

    private final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private CorsConfig corsConfig;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        LOG.info("Custom Filter Chain Bean 생성");
        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/api/auth/**")
                .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .anyRequest().permitAll();
        
        return http.build();
    }
}
