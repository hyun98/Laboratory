package com.spring.springsecuritybasic.config.jwt;

import com.spring.springsecuritybasic.config.auth.PrincipalDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
@Getter
@Deprecated
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-time}")
    private int expirationTime;

    @Value("${jwt.jwt-prefix}")
    private String jwtPrefix;

    @Value("${jwt.header-string}")
    private String headerString;

    public String getJwtPrefix() {
        return this.jwtPrefix;
    }

    public String getHeaderString() {
        return this.headerString;
    }

    public String generateJwtToken(PrincipalDetails principalDetails) {
        log.info("generateJwtToken Start");
        Date expireTime = new Date(System.currentTimeMillis() + expirationTime * 1000);
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
        
        String jwtToken = Jwts.builder()
                .setSubject("subject")
                .setExpiration(expireTime)
                .claim("payload", principalDetails)
                .signWith(key)
                .compact();
        
        log.info("jwt token : " + jwtToken);
        return jwtToken;
    }
    
    
}
