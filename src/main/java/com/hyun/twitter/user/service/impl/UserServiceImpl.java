package com.hyun.twitter.user.service.impl;

import com.hyun.twitter.user.entity.User;
import com.hyun.twitter.user.mapper.UserMapper;
import com.hyun.twitter.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
