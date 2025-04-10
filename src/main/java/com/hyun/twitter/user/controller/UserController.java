package com.hyun.twitter.user.controller;

import com.hyun.twitter.user.dto.UserRequestDto;
import com.hyun.twitter.user.service.UserService;
import com.hyun.twitter.user.dto.UserDto;
import com.hyun.twitter.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
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

    @PutMapping("/update")
    public int update(@RequestBody UserDto user) {
        log.info("update");
        User updateUser = User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .bio(user.getBio())
                .password(user.getPassword())
                .build();
        return userService.updateUser(updateUser);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestParam Long userId) {
        log.info("delete");
        return userService.deleteUser(userId);
    }
}
