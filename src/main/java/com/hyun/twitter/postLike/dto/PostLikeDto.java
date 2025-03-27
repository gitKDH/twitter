package com.hyun.twitter.postLike.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostLikeDto {
    private Long postLikeId;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
