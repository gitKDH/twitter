package com.hyun.twitter.user.service.impl;

import com.hyun.twitter.user.entity.User;
import com.hyun.twitter.user.mapper.UserMapper;
import com.hyun.twitter.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    @Transactional
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    @Transactional
    public int updateUser(User user) {
        User existingUser = userMapper.findById(user.getUserId());
        if (existingUser == null) {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }

        return userMapper.updateUser(user);
    }
}
