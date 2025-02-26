package com.hyun.twitter.user.service;

import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(@Param("userId") Long userId);
}
