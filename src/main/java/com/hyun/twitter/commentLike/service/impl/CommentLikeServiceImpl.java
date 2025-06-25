package com.hyun.twitter.commentLike.service.impl;

import com.hyun.twitter.commentLike.entity.CommentLike;
import com.hyun.twitter.commentLike.mapper.CommentLikeMapper;
import com.hyun.twitter.commentLike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {
    private final CommentLikeMapper commentLikeMapper;

    @Transactional
    @Override
    public void addCommentLike(Long commentId, Long userId) {
        CommentLike commentLike = CommentLike.builder()
                .commentId(commentId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        commentLikeMapper.addCommentLike(commentLike);
    }

    @Override
    public void unlikeComment(Long commentId, Long userId) {
        commentLikeMapper.unlikeComment(commentId, userId);
    }

    @Override
    public List<String> getUsernamesWhoLikedComment(Long commentId) {
        return commentLikeMapper.findUsernamesWhoLikedComment(commentId);
    }

    @Override
    public boolean hasUserLikedComment(Long commentId, Long userId) {
        return commentLikeMapper.hasUserLikedComment(commentId, userId);
    }

    @Override
    public int countCommentLikes(Long commentId) {
        return commentLikeMapper.countCommentLikes(commentId);
    }
}
