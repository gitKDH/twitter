package com.hyun.twitter.post.mapper;


import com.hyun.twitter.post.entity.Post;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PostMapper {
    int addPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("postId") Long postId);

    Post findByPostId(@Param("postId") Long postId);
}

