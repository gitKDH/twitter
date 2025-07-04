package com.hyun.twitter.follow.service;

import java.util.List;

public interface FollowService {
    void followUser(Long followerId, Long followingId);

    void unfollowByUserId(Long followerId, Long followingId);

    List<Long> getFollowingsByUserId(Long userId);
}