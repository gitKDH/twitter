package com.hyun.twitter.commentLike.controller;

import com.hyun.twitter.commentLike.service.CommentLikeService;
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
@RequestMapping("/api/commentlike")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/addcommentlike")
    public ResponseEntity<String> commentLike(@RequestParam Long commentId, @RequestParam Long userId) {
        commentLikeService.addCommentLike(commentId, userId);
        return ResponseEntity.ok("댓글 좋아요");
    }

    @DeleteMapping("/unlikecomment")
    public int unlikeComment(@RequestParam Long commentLikeId) {
        log.info("unlikeComment");
        return commentLikeService.unlikeComment(commentLikeId);
    }

    @GetMapping("/liked-users")
    public ResponseEntity<List<String>> getUsersWhoLikedComment(@RequestParam Long commentId) {
        List<String> usernames = commentLikeService.getUsernamesWhoLikedComment(commentId);
        return ResponseEntity.ok(usernames);
    }

    @GetMapping("/liked")
    public ResponseEntity<Boolean> hasUserLikedComment(@RequestParam Long commentId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        boolean liked = commentLikeService.hasUserLikedComment(commentId, userId);
        return ResponseEntity.ok(liked);
    }
}
