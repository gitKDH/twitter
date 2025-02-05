package com.hyun.twitter.comment.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long commentId;
    private Long postId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
