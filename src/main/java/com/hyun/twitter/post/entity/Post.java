package com.hyun.twitter.post.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
