package com.spring.springsecuritybasic.config.jwt;

import com.spring.springsecuritybasic.config.auth.PrincipalDetails;
import com.spring.springsecuritybasic.domain.User;
import com.spring.springsecuritybasic.repository.UserRepository;
import com.spring.springsecuritybasic.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private static final String AUTHORIZATION_HEADER_STRING = "Authorization";
    private static final String JWT_PREFIX = "jwt ";
    
    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(AUTHORIZATION_HEADER_STRING);
        if (header == null || !header.startsWith(JWT_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        log.info("HEADER : " + header);
        String token = request.getHeader(AUTHORIZATION_HEADER_STRING).replace(JWT_PREFIX, "");
        
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("Token expired");
        }

        // username
        String username = jwtUtil.getPayload(token);

        if (username != null) {
            // 매번 db에 접근해야 하는가?
            // token의 핵심은 무상태성인데 인증때마다 매번 db에 접근하면 token을 사용하는 의미가 퇴색?
            // db 접근 부분이 없어도 될 것 같음
            // 다중 로그인 여부, 토큰 만료시간과 같은 정책에 영향을 받을 듯하다.
            List<User> userList = userRepository.findByUsername(username);
            if (userList.isEmpty()) {
                throw new RuntimeException("No User, Authorization Failed");
            }
            User user = userList.get(0);
            
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,
                    null,
                    principalDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
