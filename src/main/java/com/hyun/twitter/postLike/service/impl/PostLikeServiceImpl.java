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
import java.util.List;

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
    public int unlikePost(Long postId, Long userId) {
        return postLikeMapper.unlikePost(postId, userId);
    }

    @Override
    public int countPostLikes(Long postId) {
        return postLikeMapper.countPostLikes(postId);
    }

    @Override
    public List<String> getUsernamesWhoLikedPost(Long postId) {
        return postLikeMapper.findUsernamesWhoLikedPost(postId);
    }

    @Override
    public boolean hasUserLikedPost(Long postId, Long userId) {
        return postLikeMapper.hasUserLikedPost(postId, userId);
    }
}
