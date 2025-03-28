package com.hyun.twitter.postLike.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLike {

    private Long postLikeId;
    private Long postId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

}
