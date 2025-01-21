package com.hyun.twitter.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Long userId;
    private String username;
    private String bio;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String role;

}
