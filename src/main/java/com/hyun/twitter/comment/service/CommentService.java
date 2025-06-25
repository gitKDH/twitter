package com.hyun.twitter.comment.service;


import com.hyun.twitter.comment.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    int addComment(Comment comment);
    int updateComment(Comment comment);
    int deleteComment(@Param("commentId") Long commentId);
    List<Comment> getCommentsByPostId(Long postId);
}
