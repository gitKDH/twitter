package com.hyun.twitter.follow.service;

public interface FollowService {
    void followUser(Long followerId, Long followingId);
}
