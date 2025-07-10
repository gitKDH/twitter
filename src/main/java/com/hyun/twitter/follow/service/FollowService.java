package com.hyun.twitter.follow.service;

import com.hyun.twitter.user.dto.UserResponseDto;

import java.util.List;

public interface FollowService {
    void followUser(Long followerId, Long followingId);

    void unfollowByUserId(Long followerId, Long followingId);

    List<UserResponseDto> getFollowings(Long userId);
    List<UserResponseDto> getFollowers(Long userId);

    boolean isFollowing(Long followerId, Long followingId);
}