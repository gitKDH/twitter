package com.hyun.twitter.postLike.controller;

import com.hyun.twitter.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postlike")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/addpostlike")
    public ResponseEntity<String> commentLike(@RequestParam Long postId, @RequestParam Long userId) {
        postLikeService.addPostLike(postId, userId);
        return ResponseEntity.ok("게시물 좋아요");
    }
}
