package com.hyun.twitter.controller;

import com.hyun.twitter.dto.UserDto;
import com.hyun.twitter.entity.User;
import com.hyun.twitter.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
