package com.hyun.twitter.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long userId;
    private String username;
    private String bio;
    private String email;
    private LocalDateTime createdAt;
}
