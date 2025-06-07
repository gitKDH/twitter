package com.hyun.twitter.postLike.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostLikeService {
    void addPostLike(@Param("postId") Long postId, @Param("userId") Long userId);
    int unlikePost(@Param("postId") Long postId, @Param("userId") Long userId);
    int countPostLikes(Long postId);
    List<String> getUsernamesWhoLikedPost(Long postId);
}
