package com.hyun.twitter.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class follow {

    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}
