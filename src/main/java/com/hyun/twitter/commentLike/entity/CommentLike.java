package com.hyun.twitter.commentLike.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentLike {

    private Long commentLikeId;
    private Long commentId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
}
