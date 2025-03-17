package com.hyun.twitter.follow.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Follow {

    private Long followId;
    private Long followerId;
    private Long followingId;
    private LocalDateTime createdAt;
}