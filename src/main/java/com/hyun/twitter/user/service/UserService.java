package com.hyun.twitter.user.service;

import com.hyun.twitter.user.dto.LoginRequestDto;
import com.hyun.twitter.user.dto.UserRequestDto;
import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    int addUser(UserRequestDto requestDto);
    int updateUser(User user);
    int deleteUser(@Param("userId") Long userId);
    String login(LoginRequestDto loginRequestDto);
}
