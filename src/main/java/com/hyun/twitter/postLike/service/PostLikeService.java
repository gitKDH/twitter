package com.hyun.twitter.postLike.service;

import org.apache.ibatis.annotations.Param;

public interface PostLikeService {
    void addPostLike(@Param("postId") Long postId, @Param("userId") Long userId);
    int unlikePost(@Param("postId") Long postId, @Param("userId") Long userId);
}
