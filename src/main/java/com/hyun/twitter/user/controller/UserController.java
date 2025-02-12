package com.hyun.twitter.user.controller;

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
    public int create(@RequestBody UserDto user) {
        log.info("create");
        User newUser = User.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .bio(user.getBio())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(LocalDateTime.now())
                .role(user.getRole())
                .build();

        return userService.addUser(newUser);
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
}
