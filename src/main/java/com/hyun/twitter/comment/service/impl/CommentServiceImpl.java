package com.hyun.twitter.comment.service.impl;

import com.hyun.twitter.comment.entity.Comment;
import com.hyun.twitter.comment.mapper.CommentMapper;
import com.hyun.twitter.comment.service.CommentService;
import com.hyun.twitter.post.entity.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        Comment existingComment = commentMapper.findByCommentId(comment.getCommentId());
        log.info("조회하려는 commentId: {}", comment.getCommentId());
        log.info("조회된 댓글: {}", existingComment);
        if (existingComment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
        Comment updateComment = Comment.builder()
                .content(comment.getContent() != null ? comment.getContent() : existingComment.getContent())
                .build();
        return commentMapper.updateComment(comment);
    }

    @Override
    public int deleteComment(Long commentId) {
        Comment existingComment = commentMapper.findByCommentId(commentId);
        if (existingComment == null) {
            throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
        }

        return commentMapper.deleteComment(commentId);
    }
}
