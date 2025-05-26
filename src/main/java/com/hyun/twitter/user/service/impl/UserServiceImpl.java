package com.hyun.twitter.user.service.impl;

import com.hyun.twitter.config.JwtUtil;
import com.hyun.twitter.passwordHistory.entity.PasswordHistory;
import com.hyun.twitter.passwordHistory.mapper.PasswordHistoryMapper;
import com.hyun.twitter.user.dto.LoginRequestDto;
import com.hyun.twitter.user.dto.PasswordChangeRequestDto;
import com.hyun.twitter.user.dto.UserProfileUpdateRequestDto;
import com.hyun.twitter.user.dto.UserRequestDto;
import com.hyun.twitter.user.entity.User;
import com.hyun.twitter.user.mapper.UserMapper;
import com.hyun.twitter.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PasswordHistoryMapper passwordHistoryMapper;

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
    public int changePassword(Long userId, PasswordChangeRequestDto dto) {
        User user = userMapper.findByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        // 현재 비밀번호 일치 여부 확인
        if (!passwordEncoder.matches(dto.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        // 최근 3개 비밀번호 조회
        List<PasswordHistory> recentPasswords = passwordHistoryMapper.findRecentPasswords(userId);

        // 새 비밀번호가 최근 사용한 것과 중복인지 확인
        for (PasswordHistory history : recentPasswords) {
            if (passwordEncoder.matches(dto.getNewPassword(), history.getPasswordHash())) {
                throw new IllegalArgumentException("최근 3회 사용한 비밀번호는 사용할 수 없습니다.");
            }
        }

        // 새 비밀번호 해시화
        String newPasswordHash = passwordEncoder.encode(dto.getNewPassword());

        // User 테이블 비밀번호 업데이트
        userMapper.updatePassword(userId, newPasswordHash);

        // PasswordHistory에 추가
        PasswordHistory history = PasswordHistory.builder()
                .userId(userId)
                .passwordHash(newPasswordHash)
                .changedAt(LocalDateTime.now())
                .build();
        passwordHistoryMapper.addPasswordHistory(history);

        return 1;
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
    public User findByEmail(String email) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("해당 이메일로 등록된 사용자가 없습니다.");
        }
        return user;
    }

    @Override
    public String login(LoginRequestDto dto) {
        User user = userMapper.findByEmail(dto.getEmail());

        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    @Transactional
    public void updateUserProfile(String email, UserProfileUpdateRequestDto requestDto) {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        User updatedUser = User.builder()
                .userId(user.getUserId())
                .username(requestDto.getUsername() != null ? requestDto.getUsername() : user.getUsername())
                .bio(requestDto.getBio() != null ? requestDto.getBio() : user.getBio())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .build();

        userMapper.updateUserProfile(updatedUser);
    }

}
