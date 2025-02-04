package com.hyun.twitter.post.mapper;


import com.hyun.twitter.post.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    int addPost(Post post);
}
