package com.hyun.twitter.follow.service.impl;

import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.follow.mapper.FollowMapper;
import com.hyun.twitter.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowMapper followMapper;

    @Override
    @Transactional
    public void followUser(Long followerId, Long followingId) {
        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .createdAt(LocalDateTime.now())
                .build();
        followMapper.followUser(follow);
    }

    @Override
    @Transactional
    public void unfollowByUserId(Long followerId, Long followingId) {
        int result = followMapper.unfollowByUserId(followerId, followingId);
        if (result == 0) {
            throw new IllegalArgumentException("팔로우 관계가 존재하지 않습니다.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Long> getFollowingsByUserId(Long userId) {
        return followMapper.findFollowingsByUserId(userId);
    }
}