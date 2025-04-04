package com.hyun.twitter.post.service;

import com.hyun.twitter.post.entity.Post;
import org.apache.ibatis.annotations.Param;

public interface PostService {
    int addPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("postId") Long postId);

}
