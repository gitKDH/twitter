package com.hyun.twitter.comment.mapper;

import com.hyun.twitter.comment.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int addComment(Comment comment);
}
