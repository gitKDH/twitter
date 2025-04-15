package com.hyun.twitter.user.mapper;

import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(@Param("userId") Long userId);

    User findByUserId(@Param("userId") Long userId);
    User findByUsername(@Param("username") String username);

}