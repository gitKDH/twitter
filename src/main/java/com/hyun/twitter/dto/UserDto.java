package com.hyun.twitter.dto;

import com.hyun.twitter.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long userId;
    private String username;
    private String bio;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private User.Role role = User.Role.USER;
    public enum Role{
        USER,ADMIN;
    }
}
