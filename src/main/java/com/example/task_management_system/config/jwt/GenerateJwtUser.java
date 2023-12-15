package com.example.task_management_system.config.jwt;

import com.example.task_management_system.entity.User;
import com.example.task_management_system.config.jwt.JwtUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;


public class GenerateJwtUser {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                mapToGrantedAuthorities(user)
        );
    }

    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(User user) {
        String role = user.isExecutor() ? "ROLE_EXECUTOR" : "ROLE_USER";
        return List.of(new SimpleGrantedAuthority(role));
    }

}

