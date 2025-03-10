package com.hyun.twitter.post.controller;

import com.hyun.twitter.post.dto.PostDto;
import com.hyun.twitter.post.entity.Post;
import com.hyun.twitter.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public int addPost(@RequestParam Long userId, @RequestBody PostDto postDto) {
        log.info("createPost by userId: {}", userId);

        Post newPost = Post.builder()
                .userId(userId)
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return postService.addPost(newPost);
    }

    @PutMapping("/update")
    public int updatePost(@RequestParam Long userId, @RequestBody PostDto post) {
        log.info("updatePost by userId: {}", userId);

        Post updatePost = Post.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .imgUrl(post.getImgUrl())
                .build();

        return postService.updatePost(updatePost);
    }

    @DeleteMapping("/delete")
    public int deletePost(@RequestParam Long postId) {
        log.info("delete");
        return postService.deletePost(postId);
    }
}
