package com.hyun.twitter.user.service;

import com.hyun.twitter.user.dto.*;
import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int addUser(UserRequestDto requestDto);
    int deleteUser(@Param("userId") Long userId);
    String login(LoginRequestDto loginRequestDto);
    int changePassword(Long userId, PasswordChangeRequestDto dto);

    User findByEmail(String email);

    void updateUserProfile(String email, UserProfileUpdateRequestDto requestDto);

    List<User> getAllUsers();

    UserResponseDto getUserInfo(Long userId);
}
