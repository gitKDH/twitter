package com.hyun.twitter.user.service.impl;

import com.hyun.twitter.user.dto.UserRequestDto;
import com.hyun.twitter.user.entity.User;
import com.hyun.twitter.user.mapper.UserMapper;
import com.hyun.twitter.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public int addUser(UserRequestDto requestDto) {
        String hashedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(hashedPassword)
                .createdAt(LocalDateTime.now())
                .role(User.Role.USER)
                .build();

        return userMapper.addUser(user);
    }

    @Override
    @Transactional
    public int updateUser(User user) {
        User existingUser = userMapper.findByUserId(user.getUserId());
        if (existingUser == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(Long userId) {
        User existingUser = userMapper.findByUserId(userId);
        if (existingUser == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        return userMapper.deleteUser(userId);
    }


}
