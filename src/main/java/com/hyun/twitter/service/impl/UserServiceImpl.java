package com.hyun.twitter.service.impl;

import com.hyun.twitter.entity.User;
import com.hyun.twitter.mapper.UserMapper;
import com.hyun.twitter.service.UserService;
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
