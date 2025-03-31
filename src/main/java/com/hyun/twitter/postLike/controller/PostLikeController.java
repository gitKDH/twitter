package com.hyun.twitter.postLike.controller;

import com.hyun.twitter.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postlike")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/addpostlike")
    public ResponseEntity<String> postLike(@RequestParam Long postId, @RequestParam Long userId) {
        postLikeService.addPostLike(postId, userId);
        return ResponseEntity.ok("게시물 좋아요");
    }

    @DeleteMapping("/unlikepost")
    public int unlikePost(@RequestParam Long postLikeId) {
        log.info("unlikePost");
        return postLikeService.unlikePost(postLikeId);
    }
}
