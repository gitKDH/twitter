package com.hyun.twitter.commentLike.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentLikeService {
    void addCommentLike(@Param("commentId") Long commentId, @Param("userId") Long userId);
    void unlikeComment(Long commentId, Long userId);
    List<String> getUsernamesWhoLikedComment(Long commentId);
    boolean hasUserLikedComment(Long commentId, Long userId);
    int countCommentLikes(Long commentId);
}
