package com.hyun.twitter.post.service.impl;

import com.hyun.twitter.post.entity.Post;
import com.hyun.twitter.post.service.PostService;
import com.hyun.twitter.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    @Override
    public int addPost(Post post){
        return postMapper.addPost(post);
    }
}
