package com.hyun.twitter.post.controller;

import com.hyun.twitter.post.dto.PostDto;
import com.hyun.twitter.post.entity.Post;
import com.hyun.twitter.post.service.PostService;
import com.hyun.twitter.user.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public int addPost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                       @RequestBody PostDto postDto) {
        Long userId = userDetails.getId();

        log.info("createPost by userId: {}", userId);

        Post newPost = Post.builder()
                .userId(userId)
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .imgUrl(postDto.getImgUrl())
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

    @GetMapping("/feed")
    public ResponseEntity<List<Post>> getFeed() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/timeline")
    public List<Post> getFollowFeed(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        return postService.getPostsByFollowing(userId);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        Post post = postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }
}
