package com.hyun.twitter.commentLike.mapper;

import com.hyun.twitter.commentLike.entity.CommentLike;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentLikeMapper {
    void addCommentLike(CommentLike commentLike);
    CommentLike findByCommentLikeId(@Param("commentLikeId") Long commentLikeId);
    int unlikeComment(@Param("commentLikeId") Long commentLikeId);
}
