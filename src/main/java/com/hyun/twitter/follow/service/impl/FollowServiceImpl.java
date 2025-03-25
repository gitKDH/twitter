package com.hyun.twitter.follow.service.impl;

import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.follow.mapper.FollowMapper;
import com.hyun.twitter.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public int unfollowUser(Long followId) {
        log.info("🔍 [unfollowUser] 찾는 followId: {}", followId);

        Follow existingFollow = followMapper.findByFollowId(followId);

        if (existingFollow == null) {
            log.warn("⚠ Follow ID {}가 존재하지만 MyBatis에서 엔티티 매핑이 안됨. resultMap 확인 필요!", followId);
            throw new IllegalArgumentException("팔로우 관계를 찾을 수 없습니다.");
        }

        // ✅ 강제 디버깅 출력
        log.info("🔍 [unfollowUser] 반환된 Follow 객체: {}", existingFollow);
        log.info("🔍 [unfollowUser] Follow ID: {}", existingFollow.getFollowId());
        log.info("🔍 [unfollowUser] Follower ID: {}", existingFollow.getFollowerId());
        log.info("🔍 [unfollowUser] Following ID: {}", existingFollow.getFollowingId());

        return followMapper.unfollowUser(followId);
    }
}
