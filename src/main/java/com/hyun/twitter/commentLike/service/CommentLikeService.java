package com.hyun.twitter.commentLike.service;

import org.apache.ibatis.annotations.Param;

public interface CommentLikeService {
    void addCommentLike(@Param("commentId") Long commentId, @Param("userId") Long userId);
    int unlikeComment(@Param("commentLikeId") Long commentLikeId);
}
