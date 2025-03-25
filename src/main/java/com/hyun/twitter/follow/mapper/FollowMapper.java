package com.hyun.twitter.follow.mapper;

import com.hyun.twitter.follow.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {
    void followUser(Follow follow);
    int unfollowUser(@Param("followId") Long followId);
    Follow findByFollowId(@Param("followId") Long followId);
}
