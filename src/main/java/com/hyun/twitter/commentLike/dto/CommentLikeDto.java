package com.hyun.twitter.commentLike.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentLikeDto {
    private Long commentLikeId;
    private Long commentId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
