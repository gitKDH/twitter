package com.hyun.twitter.commentLike.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentLike {

    private Long commentLikeId;
    private Long commentId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
