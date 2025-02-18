package com.hyun.twitter.post.service;

import com.hyun.twitter.post.entity.Post;

public interface PostService {
    int addPost(Post post);
    int updatePost(Post post);

}
