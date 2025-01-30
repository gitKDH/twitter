package com.hyun.twitter.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    //유효성 검사는 DTO나 서비스 계층에서 구현하기

    private Long userId;
    private String username;
    private String bio;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Role role = Role.USER;
    public enum Role{
        USER,ADMIN;
    }

    public void markAsDeleted() {
        this.deletedAt = LocalDateTime.now();
    }

}
