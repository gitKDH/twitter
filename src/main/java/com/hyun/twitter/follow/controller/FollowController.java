package com.hyun.twitter.follow.controller;

import com.hyun.twitter.follow.service.FollowService;
import com.hyun.twitter.user.dto.UserResponseDto;
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
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam Long followingId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long followerId = userDetails.getId();
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("팔로우 완료");
    }

    @DeleteMapping("/unfollow-by-userid")
    public ResponseEntity<String> unfollowByUserId(@RequestParam Long followingId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long followerId = userDetails.getId();
        followService.unfollowByUserId(followerId, followingId);
        return ResponseEntity.ok("언팔로우 완료");
    }

    @GetMapping("/following")
    public ResponseEntity<List<UserResponseDto>> getFollowings(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        List<UserResponseDto> followingIds = followService.getFollowings(userId);
        return ResponseEntity.ok(followingIds);
    }

    @GetMapping("/followers")
    public ResponseEntity<List<UserResponseDto>> getFollowers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getId();
        List<UserResponseDto> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }
}