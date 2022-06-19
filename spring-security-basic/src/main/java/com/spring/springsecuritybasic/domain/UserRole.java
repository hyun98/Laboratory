package com.spring.springsecuritybasic.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "USER_ROLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRole implements GrantedAuthority {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public UserRole(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String getAuthority() {
        return roleName;
    }
}
