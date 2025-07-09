package com.hyun.twitter.follow.mapper;

import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FollowMapper {
    void followUser(Follow follow);

    int unfollowByUserId(@Param("followerId") Long followerId,
                         @Param("followingId") Long followingId);

    Follow findByFollowId(@Param("followId") Long followId);

    List<User> findFollowingsByUserId(@Param("userId") Long userId);
    List<User> findFollowersByUserId(@Param("userId") Long userId);
}