package com.spring.springsecuritybasic.controller;

import com.spring.springsecuritybasic.domain.User;
import com.spring.springsecuritybasic.dto.LoginDto;
import com.spring.springsecuritybasic.dto.SignInDto;
import com.spring.springsecuritybasic.dto.TokenDto;
import com.spring.springsecuritybasic.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthApiImpl implements AuthApi{

    private final UserService userService;
    
    @Override
    public ResponseEntity getTestV1() {
        log.info("Test API 호출");
        return new ResponseEntity("Test API Called", HttpStatus.OK);
    }

    @Override
    public ResponseEntity loginV1(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        log.info("Login API 호출");

        TokenDto tokenDto = userService.login(loginDto);
        Cookie refreshToken = new Cookie("refreshToken", tokenDto.getRefreshToken());
        refreshToken.setHttpOnly(true);
        refreshToken.setMaxAge(60*60*24);
        refreshToken.setPath("/");
        response.addCookie(refreshToken);
        
        return new ResponseEntity(new AccessToken(tokenDto.getAccessToken()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity logoutV1(HttpServletResponse response) {
        log.info("Logout API 호출");

        Cookie refreshToken = new Cookie("refreshToken", null);
        refreshToken.setMaxAge(0);
        refreshToken.setPath("/");
        response.addCookie(refreshToken);

        return new ResponseEntity("Logout Success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity signInV1(@RequestBody SignInDto userSignInDto) {
        log.info("Sign In API 호출");

        User user = userService.registerNewUserAccount(userSignInDto);
        
        return new ResponseEntity(user.getId(), HttpStatus.OK);
    }
    
    @Data
    @AllArgsConstructor
    static class AccessToken{
        private String accessToken;
    }
}
