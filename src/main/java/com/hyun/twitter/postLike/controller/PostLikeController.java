package com.hyun.twitter.postLike.controller;

import com.hyun.twitter.postLike.service.PostLikeService;
import com.hyun.twitter.user.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postlike")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/addpostlike")
    public ResponseEntity<String> addPostLike(@RequestParam Long postId,
                                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        postLikeService.addPostLike(postId, userId);
        return ResponseEntity.ok("좋아요 완료");
    }

    @DeleteMapping("/unlikepost")
    public ResponseEntity<String> unlikePost(@RequestParam Long postId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        postLikeService.unlikePost(postId, userId);
        return ResponseEntity.ok("좋아요 취소");
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countPostLikes(@RequestParam Long postId) {
        int likeCount = postLikeService.countPostLikes(postId);
        return ResponseEntity.ok(likeCount);
    }

    @GetMapping("/liked-users")
    public ResponseEntity<List<String>> getUsersWhoLikedPost(@RequestParam Long postId) {
        List<String> usernames = postLikeService.getUsernamesWhoLikedPost(postId);
        return ResponseEntity.ok(usernames);
    }

    @GetMapping("/liked")
    public ResponseEntity<Boolean> hasUserLikedPost(@RequestParam Long postId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        boolean liked = postLikeService.hasUserLikedPost(postId, userId);
        return ResponseEntity.ok(liked);
    }
}
