package com.hyun.twitter.comment.service;


import com.hyun.twitter.comment.entity.Comment;

public interface CommentService {
    int addComment(Comment comment);
    int updateComment(Comment comment);
}
