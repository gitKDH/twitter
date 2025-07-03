package com.hyun.twitter.follow.controller;


import com.hyun.twitter.follow.service.FollowService;
import com.hyun.twitter.user.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @RequestParam Long followingId) {
        Long followerId = userDetails.getId();
        followService.followUser(followerId, followingId);
        return ResponseEntity.ok("팔로우");
    }

    @DeleteMapping("/unfollow")
    public int unfollowUser(@RequestParam Long followId) {
        log.info("unfollow");
        return followService.unfollowUser(followId);
    }


}
