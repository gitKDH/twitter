package com.hyun.twitter.postLike.service.impl;


import com.hyun.twitter.follow.entity.Follow;
import com.hyun.twitter.postLike.entity.PostLike;
import com.hyun.twitter.postLike.mapper.PostLikeMapper;
import com.hyun.twitter.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeMapper postLikeMapper;

    @Transactional
    @Override
    public void addPostLike(Long postId, Long userId) {
        PostLike postLike = PostLike.builder()
                .postId(postId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        postLikeMapper.addPostLike(postLike);
    }

    @Override
    @Transactional
    public int unlikePost(Long postLikeId) {
        log.info("찾는 postLikeId: {}", postLikeId);

        PostLike existingPostLike = postLikeMapper.findByPostLikeId(postLikeId);

        if (existingPostLike == null) {
            throw new IllegalArgumentException("좋아요 관계를 찾을 수 없습니다.");
        }

        log.info("반환된 PostLike 객체: {}", existingPostLike);
        return postLikeMapper.unlikePost(postLikeId);
    }
}
