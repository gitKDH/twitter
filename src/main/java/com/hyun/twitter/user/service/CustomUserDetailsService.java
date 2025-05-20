package com.hyun.twitter.user.service;

import com.hyun.twitter.user.entity.User;
import com.hyun.twitter.user.mapper.UserMapper;
import com.hyun.twitter.user.service.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email);
        }

        log.info("UserDetailsService 로딩된 유저: {}", user);
        return new UserDetailsImpl(user);
    }
}
