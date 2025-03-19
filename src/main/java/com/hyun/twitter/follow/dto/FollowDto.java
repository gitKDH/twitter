package com.hyun.twitter.follow.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FollowDto {
    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
