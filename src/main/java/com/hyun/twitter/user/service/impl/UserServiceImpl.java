package com.hyun.twitter.user.service.impl;

import com.hyun.twitter.config.JwtUtil;
import com.hyun.twitter.user.dto.LoginRequestDto;
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
    private final JwtUtil jwtUtil;

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
    public int deleteUser(Long userId) {
        User existingUser = userMapper.findByUserId(userId);
        if (existingUser == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        return userMapper.deleteUser(userId);
    }

    @Override
    public String login(LoginRequestDto dto) {
        User user = userMapper.findByEmail(dto.getEmail());

        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}
