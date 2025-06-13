package com.hyun.twitter.user.controller;

import com.hyun.twitter.user.dto.*;
import com.hyun.twitter.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public int create(@RequestBody UserRequestDto requestDto) {
        log.info("회원가입 요청: {}", requestDto);
        return userService.addUser(requestDto);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PasswordChangeRequestDto requestDto) {

        Long userId = userService.findByEmail(userDetails.getUsername()).getUserId();
        userService.changePassword(userId, requestDto);

        return ResponseEntity.ok("비밀번호 변경.");
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam Long userId) {
        log.info("delete");
        return userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String token = userService.login(loginRequestDto);
        response.setHeader("Authorization", "Bearer " + token);
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(new UserResponseDto(username));
    }

    @PatchMapping("/profile")
    public ResponseEntity<String> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserProfileUpdateRequestDto requestDto) {

        String email = userDetails.getUsername();
        userService.updateUserProfile(email, requestDto);

        return ResponseEntity.ok("프로필 수정.");
    }
}
