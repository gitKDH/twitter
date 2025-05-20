package com.hyun.twitter.user.mapper;

import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int updatePassword(@Param("userId") Long userId, @Param("password") String password);
    int deleteUser(@Param("userId") Long userId);

    User findByUserId(@Param("userId") Long userId);
    User findByEmail(@Param("email") String email);

}