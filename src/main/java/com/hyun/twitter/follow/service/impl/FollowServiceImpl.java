package com.hyun.twitter.follow.service.impl;

import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.follow.mapper.FollowMapper;
import com.hyun.twitter.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowMapper followMapper;

    @Override
    public void followUser(Long followerId, Long followingId) {
        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .createdAt(LocalDateTime.now())
                .build();
        followMapper.followUser(follow);
    }
}
