package com.hyun.twitter.passwordHistory.entity;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordHistory {

    private Long historyId;
    private Long userId;
    private String passwordHash;
    private LocalDateTime changedAt;
}
