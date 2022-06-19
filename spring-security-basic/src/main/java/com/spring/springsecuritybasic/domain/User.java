package com.spring.springsecuritybasic.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;
    
    private String username;
    
    private String password;
    
    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "USER_ID")})
    private List<UserRole> roles;

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.roles = null;
    }

    public void addUserRole(UserRole role) {
        this.roles.add(role);
    }
    
}
