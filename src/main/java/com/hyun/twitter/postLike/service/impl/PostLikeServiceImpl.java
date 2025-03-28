package com.hyun.twitter.postLike.service.impl;


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
}
