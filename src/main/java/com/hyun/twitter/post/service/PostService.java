package com.hyun.twitter.post.service;

import com.hyun.twitter.post.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostService {
    int addPost(Post post);
    int updatePost(Post post);
    int deletePost(@Param("postId") Long postId);
    List<Post> getAllPosts();
    List<Post> getPostsByFollowing(Long userId);

}
