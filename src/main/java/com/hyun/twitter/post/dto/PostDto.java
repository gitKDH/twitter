package com.hyun.twitter.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String imgUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
