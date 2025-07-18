package com.hyun.twitter.postLike.mapper;

import com.hyun.twitter.postLike.entity.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostLikeMapper {
    void addPostLike(PostLike postLike);
    PostLike findByPostLikeId(@Param("postLikeId") Long postLikeId);
    int unlikePost(@Param("postId") Long postId, @Param("userId") Long userId);
    int countPostLikes(@Param("postId") Long postId);
    List<String> findUsernamesWhoLikedPost(@Param("postId") Long postId);
    boolean hasUserLikedPost(@Param("postId") Long postId, @Param("userId") Long userId);
}
