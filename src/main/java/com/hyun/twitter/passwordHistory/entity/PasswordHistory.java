package com.hyun.twitter.passwordHistory.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordHistory {

    private Long historyId;
    private Long userId;
    private String passwordHash;
    private LocalDateTime createdAt;
}
