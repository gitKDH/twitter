package com.hyun.twitter.follow.mapper;

import com.hyun.twitter.follow.entity.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {
    void followUser(Follow follow);

    int unfollowByUserId(@Param("followerId") Long followerId,
                         @Param("followingId") Long followingId);

    Follow findByFollowId(@Param("followId") Long followId);

    List<Long> findFollowingsByUserId(@Param("userId") Long userId);
}