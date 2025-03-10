package com.hyun.twitter.comment.mapper;

import com.hyun.twitter.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    int addComment(Comment comment);
    int updateComment(Comment comment);
    int deleteComment(@Param("commentId") Long commentId);

    Comment findByCommentId(@Param("commentId") Long commentId);
}
