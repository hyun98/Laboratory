package com.spring.springsecuritybasic.controller;

import com.spring.springsecuritybasic.domain.User;
import com.spring.springsecuritybasic.domain.UserRole;
import com.spring.springsecuritybasic.dto.LoginDto;
import com.spring.springsecuritybasic.dto.SignInDto;
import com.spring.springsecuritybasic.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi{

    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity getTestV1() {
        log.info("Test API 호출");
        return new ResponseEntity("Test API Called", HttpStatus.OK);
    }

    @Override
    public ResponseEntity loginV1(LoginDto loginDto) {
        log.info("Login API 호출");
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity logoutV1() {
        log.info("Logout API 호출");
        return new ResponseEntity("Logout Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity signInV1(SignInDto userSignInDto) {
        log.info("Sign In API 호출");

        User user = userRepository.save(
                new User(userSignInDto.getName(), userSignInDto.getPassword(), userSignInDto.getUserRole()));
        return new ResponseEntity(user.getId(), HttpStatus.OK);
    }
}
