package com.hyun.twitter.follow.service;

import org.apache.ibatis.annotations.Param;

public interface FollowService {
    void followUser(Long followerId, Long followingId);
    int unfollowUser(@Param("followId") Long followId);

}
