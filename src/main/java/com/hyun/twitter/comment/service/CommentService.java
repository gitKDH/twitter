package com.hyun.twitter.comment.service;


import com.hyun.twitter.comment.entity.Comment;
import org.apache.ibatis.annotations.Param;

public interface CommentService {
    int addComment(Comment comment);
    int updateComment(Comment comment);
    int deleteComment(@Param("commentId") Long commentId);
}
