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
}
