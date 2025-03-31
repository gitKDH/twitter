package com.hyun.twitter.postLike.mapper;

import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.postLike.entity.PostLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikeMapper {
    void addPostLike(PostLike postLike);
    PostLike findByPostLikeId(@Param("postLikeId") Long postLikeId);
    int unlikePost(@Param("postLikeId") Long postLikeId);
}
