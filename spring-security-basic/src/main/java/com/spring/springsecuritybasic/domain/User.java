package com.spring.springsecuritybasic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    
    private String username;
    
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<UserRole> roles = new ArrayList<>();

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.roles.add(userRole);
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = null;
    }

    public void addUserRole(UserRole role) {
        this.roles.add(role);
    }
    
}
