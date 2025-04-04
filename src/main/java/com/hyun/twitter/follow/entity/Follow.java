package com.hyun.twitter.follow.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}