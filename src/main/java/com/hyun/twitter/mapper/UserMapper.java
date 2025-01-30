package com.hyun.twitter.mapper;

import com.hyun.twitter.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    int addUser(User user);

}