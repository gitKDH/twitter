package com.hyun.twitter.comment.service.impl;

import com.hyun.twitter.comment.entity.Comment;
import com.hyun.twitter.comment.mapper.CommentMapper;
import com.hyun.twitter.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        if (existingComment == null) {
            throw new IllegalArgumentException("댓글을 찾을 수 없습니다.");
        }
        Comment updateComment = Comment.builder()
                .content(comment.getContent() != null ? comment.getContent() : existingComment.getContent())
                .build();
        return commentMapper.updateComment(comment);
    }
}
