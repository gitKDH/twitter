package com.hyun.twitter.follow.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Follow {

    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}