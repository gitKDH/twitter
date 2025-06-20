package com.hyun.twitter.post.service.impl;

import com.hyun.twitter.post.entity.Post;
import com.hyun.twitter.post.service.PostService;
import com.hyun.twitter.post.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    @Override
    public int addPost(Post post){
        return postMapper.addPost(post);
    }

    @Override
    public int updatePost(Post post) {
        Post existingPost = postMapper.findByPostId(post.getPostId());
        if (existingPost == null) {
            throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
        }
        Post updatePost = Post.builder()
                .title(post.getTitle() != null ? post.getTitle() : existingPost.getTitle())
                .content(post.getContent() != null ? post.getContent() : existingPost.getContent())
                .imgUrl(post.getImgUrl() != null ? post.getImgUrl() : existingPost.getImgUrl())
                .build();
        return postMapper.updatePost(post);
    }

    @Override
    public int deletePost(Long postId) {
        Post existingPost = postMapper.findByPostId(postId);
        if (existingPost == null) {
            throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
        }

        return postMapper.deletePost(postId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postMapper.findAllPosts();
    }

    @Override
    public List<Post> getPostsByFollowing(Long userId) {
        return postMapper.findPostsByFollowing(userId);
    }

    @Override
    public Post findPostById(Long postId) {
        Post post = postMapper.findByPostId(postId);
        if (post == null) {
            throw new IllegalArgumentException("게시물을 찾을 수 없습니다.");
        }
        return post;
    }
}
