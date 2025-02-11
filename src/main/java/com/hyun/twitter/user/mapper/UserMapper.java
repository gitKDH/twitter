package com.hyun.twitter.user.mapper;

import com.hyun.twitter.user.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    int addUser(User user);
    int updateUser(User user);

    User findById(@Param("userId") Long userId);
}