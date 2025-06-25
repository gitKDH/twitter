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
    public ResponseEntity<String> addCommentLike(@RequestParam Long commentId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        commentLikeService.addCommentLike(commentId, userId);
        return ResponseEntity.ok("댓글 좋아요 성공");
    }

    @DeleteMapping("/unlikecomment")
    public ResponseEntity<String> unlikeComment(@RequestParam Long commentId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        commentLikeService.unlikeComment(commentId, userId);
        return ResponseEntity.ok("댓글 좋아요 취소 완료");
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

    @GetMapping("/count")
    public ResponseEntity<Integer> countCommentLikes(@RequestParam Long commentId) {
        int count = commentLikeService.countCommentLikes(commentId);
        return ResponseEntity.ok(count);
    }
}
