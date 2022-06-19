package com.spring.springsecuritybasic.dto;

import com.spring.springsecuritybasic.domain.UserRole;
import lombok.Data;

@Data
public class SignInDto {
    private String name;
    private String password;
    private UserRole userRole;
}
