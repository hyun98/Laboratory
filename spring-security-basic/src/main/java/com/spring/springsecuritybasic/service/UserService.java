package com.spring.springsecuritybasic.service;

import com.spring.springsecuritybasic.config.auth.PrincipalDetails;
import com.spring.springsecuritybasic.domain.User;
import com.spring.springsecuritybasic.domain.UserRole;
import com.spring.springsecuritybasic.dto.LoginDto;
import com.spring.springsecuritybasic.dto.SignInDto;
import com.spring.springsecuritybasic.dto.TokenDto;
import com.spring.springsecuritybasic.repository.RoleRepository;
import com.spring.springsecuritybasic.repository.UserRepository;
import com.spring.springsecuritybasic.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenDto login(LoginDto loginDto) {
        
        User user = getByCredentials(loginDto.getUsername(), loginDto.getPassword());
        if (user == null) {
            throw new RuntimeException("User Not Found");
        }
        
        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);
        return new TokenDto(accessToken, refreshToken);
    }

    public void logout() {
        
    }
    
    @Transactional
    public User registerNewUserAccount(SignInDto signInDto) {
        UserRole roleUser = roleRepository.findByRoleName("ROLE_USER").get(0);
        
        User user = new User(
                signInDto.getUsername(),
                passwordEncoder.encode(signInDto.getPassword()),
                roleUser);

        return userRepository.save(user);
    }

    private User getByCredentials(String username, String password) {
        List<User> userList = userRepository.findByUsername(username);

        if (userList.isEmpty()) {
            throw new RuntimeException("No User");
        }

        User user = userList.get(0);
        
        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
