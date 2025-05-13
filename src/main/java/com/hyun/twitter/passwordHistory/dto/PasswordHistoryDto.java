package com.hyun.twitter.passwordHistory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PasswordHistoryDto {
    private Long historyId;
    private Long userId;
    private String passwordHash;
    private LocalDateTime changedAt;
}
