package com.hyun.twitter.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostLike {

    private Long postLikeId;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

}
