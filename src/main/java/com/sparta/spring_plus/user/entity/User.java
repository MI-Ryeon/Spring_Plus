package com.sparta.spring_plus.user.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
