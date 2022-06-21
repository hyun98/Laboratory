package com.spring.springsecuritybasic.util;


import com.spring.springsecuritybasic.config.auth.PrincipalDetails;
import com.spring.springsecuritybasic.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 60;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

    public static final String ACCESS_TOKEN_NAME = "accessToken";
    public static final String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getPayload(String token) {
        return extractAllClaims(token).get("payload", String.class);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateAccessToken(User user) {
        
        return doGenerateToken(user.getUsername(), TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(User user) {
        return doGenerateToken(user.getUsername(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(String username, long expireTime) {
        log.info("generateJwtToken Start");
        
        Claims claims = Jwts.claims();
        claims.put("payload", username);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();

        log.info("token : " + jwt);
        return jwt;
    }

    public Boolean validateToken(String token, PrincipalDetails principalDetails) {
        final String username = getPayload(token);

        return (username.equals(principalDetails.getUsername()) && !isTokenExpired(token));
    }

}
