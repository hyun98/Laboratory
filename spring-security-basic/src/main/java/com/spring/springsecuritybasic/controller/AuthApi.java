package com.spring.springsecuritybasic.controller;

import com.spring.springsecuritybasic.config.auth.PrincipalDetails;
import com.spring.springsecuritybasic.dto.LoginDto;
import com.spring.springsecuritybasic.dto.SignInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api")
public interface AuthApi {

    @GetMapping(value = "/test", produces = "application/json")
    ResponseEntity getTestV1(@AuthenticationPrincipal PrincipalDetails principalDetails);

    @PostMapping(value = "/auth/login", 
            consumes = "application/json", 
            produces = "application/json")
    ResponseEntity loginV1(@RequestBody LoginDto loginDto, HttpServletResponse response);

    @PostMapping(value = "/auth/logout",
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity logoutV1(HttpServletResponse response);

    @PostMapping(value = "/auth/signin",
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity signInV1(@RequestBody SignInDto userSignInDto);
}
