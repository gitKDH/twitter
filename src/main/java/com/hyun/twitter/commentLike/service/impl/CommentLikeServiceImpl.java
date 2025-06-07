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
    @Transactional
    public int unlikeComment(Long commentLikeId) {
        log.info("찾는 commentLikeId: {}", commentLikeId);

        CommentLike existingCommentLike = commentLikeMapper.findByCommentLikeId(commentLikeId);

        if (existingCommentLike == null) {
            throw new IllegalArgumentException("좋아요 관계를 찾을 수 없습니다.");
        }

        log.info("반환된 CommentLike 객체: {}", existingCommentLike);
        return commentLikeMapper.unlikeComment(commentLikeId);
    }

    @Override
    public List<String> getUsernamesWhoLikedComment(Long commentId) {
        return commentLikeMapper.findUsernamesWhoLikedComment(commentId);
    }
}
